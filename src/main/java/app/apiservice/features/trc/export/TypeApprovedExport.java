package app.apiservice.features.trc.export;

import app.apiservice.common.CustomMappingStrategy;
import app.apiservice.common.db.PropertiesReader;
import app.apiservice.common.exceptions.ResourceServicesException;
import app.apiservice.common.features.sysparam.ConfigTags;
import app.apiservice.common.features.sysparam.ConfigurationManagementServiceImpl;
import app.apiservice.common.model.FileDetails;
import app.apiservice.entity.app.SystemConfigurationDb;
import app.apiservice.entity.app.TRCDataManagementEntity;
import app.apiservice.features.trc.csv_file_model.TypeApproveFileModel;
import app.apiservice.features.trc.paging.TypeApprovedPaging;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class TypeApprovedExport {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private ConfigurationManagementServiceImpl configurationManagementServiceImpl;
    private TypeApprovedPaging typeApprovedPaging;

    public TypeApprovedExport(ConfigurationManagementServiceImpl configurationManagementServiceImpl, TypeApprovedPaging typeApprovedPaging) {
        this.configurationManagementServiceImpl = configurationManagementServiceImpl;
        this.typeApprovedPaging = typeApprovedPaging;
    }

    @Autowired
    private PropertiesReader propertiesReader;

    public FileDetails export(TRCDataManagementEntity trcDataManagementEntity, String subFeature) {
        String fileName = null;
        Writer writer = null;
        Integer pageNo = 0;
        Integer pageSize = Integer.valueOf(configurationManagementServiceImpl.findByTag("file.max-file-record").getValue());

        TypeApproveFileModel fileModel = null;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

        SystemConfigurationDb filepath = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_dir);
        SystemConfigurationDb link = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_link);
        logger.info("File Path :  [" + filepath + "] & Link : [" + link + "]");

        StatefulBeanToCsvBuilder<TypeApproveFileModel> builder = null;
        StatefulBeanToCsv<TypeApproveFileModel> csvWriter = null;
        List<TypeApproveFileModel> fileRecords = null;
        CustomMappingStrategy<TypeApproveFileModel> mappingStrategy = new CustomMappingStrategy<>();

        try {
            List<TRCDataManagementEntity> list = typeApprovedPaging.findAll(trcDataManagementEntity, pageNo, pageSize).getContent();
            fileName = LocalDateTime.now().format(dtf2).replace(" ", "_") + "_" + subFeature + ".csv";
            writer = Files.newBufferedWriter(Paths.get(filepath.getValue() + fileName));
            mappingStrategy.setType(TypeApproveFileModel.class);

            builder = new StatefulBeanToCsvBuilder<>(writer);
            csvWriter = builder.withMappingStrategy(mappingStrategy).withSeparator(',')
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();

            if (list.size() > 0) {
                fileRecords = new ArrayList<TypeApproveFileModel>();
                for (TRCDataManagementEntity data : list) {
                    fileModel = new TypeApproveFileModel();
                    fileModel.setCreatedOn(data.getCreatedOn());
                    fileModel.setFileName(data.getFileName());
                    fileModel.setTransactionId(data.getTransactionId());
                    fileModel.setStatus(data.getStatus());
                    if (Objects.nonNull(data.getUser())) {
                        fileModel.setUploadedBy(data.getUser().getUsername());
                    } else {
                        fileModel.setUploadedBy(null);
                    }
                    fileRecords.add(fileModel);
                }
                logger.info("Exported data : [" + fileRecords + "]");
                csvWriter.write(fileRecords);
            } else {
                csvWriter.write(new TypeApproveFileModel());
            }
            logger.info("fileName [" + fileName + "] filePath [" + filepath + "] download link [" + link.getValue() + "]");

            FileDetails fileDetails = new FileDetails(fileName, filepath.getValue(),
                    link.getValue().replace("$LOCAL_IP", propertiesReader.localIp) + fileName);
            logger.info("export file Details [" + fileDetails + "]");
            return fileDetails;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
        } finally {
            try {

                if (writer != null)
                    writer.close();
            } catch (IOException e) {
            }
        }
    }
}
