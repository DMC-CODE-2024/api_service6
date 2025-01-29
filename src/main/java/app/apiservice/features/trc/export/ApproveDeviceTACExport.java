package app.apiservice.features.trc.export;

import app.apiservice.common.CustomMappingStrategy;
import app.apiservice.common.db.PropertiesReader;
import app.apiservice.common.exceptions.ResourceServicesException;
import app.apiservice.common.features.sysparam.ConfigTags;
import app.apiservice.common.features.sysparam.ConfigurationManagementServiceImpl;
import app.apiservice.common.model.FileDetails;
import app.apiservice.entity.app.MobileDeviceRepository;
import app.apiservice.entity.app.SystemConfigurationDb;
import app.apiservice.features.trc.csv_file_model.ApproveDeviceTACFileModel;
import app.apiservice.features.trc.paging.GSMATacPaging;
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
public class ApproveDeviceTACExport {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private ConfigurationManagementServiceImpl configurationManagementServiceImpl;
    private GSMATacPaging gsmaTacPaging;

    public ApproveDeviceTACExport(ConfigurationManagementServiceImpl configurationManagementServiceImpl, GSMATacPaging gsmaTacPaging) {
        this.configurationManagementServiceImpl = configurationManagementServiceImpl;
        this.gsmaTacPaging = gsmaTacPaging;
    }

    @Autowired
    private PropertiesReader propertiesReader;

    public FileDetails export(MobileDeviceRepository mobileDeviceRepository, String exportFileName) {
        String fileName = null;
        Writer writer = null;
        Integer pageNo = 0;
        Integer pageSize = Integer.valueOf(configurationManagementServiceImpl.findByTag("file.max-file-record").getValue());

        ApproveDeviceTACFileModel fileModel = null;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

        SystemConfigurationDb filepath = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_dir);
        SystemConfigurationDb link = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_link);
        logger.info("File Path :  [" + filepath + "] & Link : [" + link + "]");

        StatefulBeanToCsvBuilder<ApproveDeviceTACFileModel> builder = null;
        StatefulBeanToCsv<ApproveDeviceTACFileModel> csvWriter = null;
        List<ApproveDeviceTACFileModel> fileRecords = null;
        CustomMappingStrategy<ApproveDeviceTACFileModel> mappingStrategy = new CustomMappingStrategy<>();

        try {
            List<MobileDeviceRepository> list = gsmaTacPaging.findAll(mobileDeviceRepository, pageNo, pageSize).getContent();
            fileName = LocalDateTime.now().format(dtf2).replace(" ", "_") + exportFileName + ".csv";
            writer = Files.newBufferedWriter(Paths.get(filepath.getValue() + fileName));
            mappingStrategy.setType(ApproveDeviceTACFileModel.class);

            builder = new StatefulBeanToCsvBuilder<>(writer);
            csvWriter = builder.withMappingStrategy(mappingStrategy).withSeparator(',').withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();

            if (list.size() > 0) {
                fileRecords = new ArrayList<ApproveDeviceTACFileModel>();
                for (MobileDeviceRepository data : list) {
                    fileModel = new ApproveDeviceTACFileModel();
                    fileModel.setModelName(data.getModelName());
                    fileModel.setManufacturer(data.getManufacturer());
                    fileModel.setManufacturingLocation(data.getManufacturingLocation());
                    fileModel.setOs(data.getOs());
                    if (Objects.nonNull(data.getLaunchDate())) {
                        fileModel.setLaunchDate(data.getLaunchDate().format(dtf));
                    } else {
                        fileModel.setLaunchDate("");
                    }
                    fileModel.setDeviceType(data.getDeviceType());
                    fileModel.setSimSlot(data.getSimSlot());
                    fileModel.setTac(data.getDeviceId());
                    fileRecords.add(fileModel);
                }
                logger.info("Exported data : [" + fileRecords + "]");
                csvWriter.write(fileRecords);
            } else {
                csvWriter.write(new ApproveDeviceTACFileModel());
            }
            logger.info("fileName [" + fileName + "] filePath [" + filepath + "] download link [" + link.getValue() + "]");

            //    auditTrailOperation(msisdnSeriesModel, Features.OPERATOR_CONFIGURATION, SubFeatures.EXPORT);

            FileDetails fileDetails = new FileDetails(fileName, filepath.getValue(), link.getValue().replace("$LOCAL_IP", propertiesReader.localIp) + fileName);
            logger.info("export file Details [" + fileDetails + "]");
            return fileDetails;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
        } finally {
            try {

                if (writer != null) writer.close();
            } catch (IOException e) {
            }
        }
    }
}
