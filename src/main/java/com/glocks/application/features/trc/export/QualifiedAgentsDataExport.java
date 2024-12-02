package com.glocks.application.features.trc.export;

import com.glocks.application.common.CustomMappingStrategy;
import com.glocks.application.common.db.PropertiesReader;
import com.glocks.application.common.exceptions.ResourceServicesException;
import com.glocks.application.common.model.FileDetails;
import com.glocks.application.entity.app.SystemConfigurationDb;
import com.glocks.application.entity.app.TRCQualifiedAgentsDataEntity;
import com.glocks.application.features.trc.csv_file_model.QualifiedAgentsDataFileModel;
import com.glocks.application.features.trc.csv_file_model.TypeApproveFileModel;
import com.glocks.application.features.trc.paging.QualifiedAgentsDataPaging;
import com.glocks.application.features.trc.service.ExportService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class QualifiedAgentsDataExport {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private ExportService exportService;
    private QualifiedAgentsDataPaging qualifiedAgentsDataPaging;

    public QualifiedAgentsDataExport(ExportService exportService, QualifiedAgentsDataPaging qualifiedAgentsDataPaging) {
        this.exportService = exportService;
        this.qualifiedAgentsDataPaging = qualifiedAgentsDataPaging;
    }

    @Autowired
    private PropertiesReader propertiesReader;

    public FileDetails export(TRCQualifiedAgentsDataEntity trcQualifiedAgentsDataEntity, String exportFileName) {
        String fileName = null;
        Writer writer = null;
        int pageNo = 0;
        int pageSize = exportService.pageSize();
        QualifiedAgentsDataFileModel fileModel = null;
        DateTimeFormatter dtf = exportService.forDate();
        DateTimeFormatter dtf2 = exportService.forFile();
        SystemConfigurationDb filepath = exportService.filepath();
        SystemConfigurationDb link = exportService.link();
        logger.info("File Path :  [" + filepath + "] & Link : [" + link + "]");

        StatefulBeanToCsvBuilder<QualifiedAgentsDataFileModel> builder = null;
        StatefulBeanToCsv<QualifiedAgentsDataFileModel> csvWriter = null;
        List<QualifiedAgentsDataFileModel> fileRecords = null;
        CustomMappingStrategy<QualifiedAgentsDataFileModel> mappingStrategy = new CustomMappingStrategy<>();

        try {
            List<TRCQualifiedAgentsDataEntity> list = qualifiedAgentsDataPaging.findAll(trcQualifiedAgentsDataEntity, pageNo, pageSize).getContent();
            fileName = LocalDateTime.now().format(dtf2).replace(" ", "_") + exportFileName + ".csv";
            writer = Files.newBufferedWriter(Paths.get(filepath.getValue() + fileName));
            mappingStrategy.setType(QualifiedAgentsDataFileModel.class);

            builder = new StatefulBeanToCsvBuilder<>(writer);
            csvWriter = builder.withMappingStrategy(mappingStrategy).withSeparator(',')
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();

            if (!list.isEmpty()) {
                fileRecords = new ArrayList<QualifiedAgentsDataFileModel>();
                for (TRCQualifiedAgentsDataEntity data : list) {
                    fileModel = new QualifiedAgentsDataFileModel()
                            .setNo(String.valueOf(data.getNo()))
                            .setCompanyName(data.getCompanyName())
                            .setCompanyId(data.getCompanyId())
                            .setPhoneNumber(data.getPhoneNumber())
                            .setEmail(data.getEmail())
                            .setExpiryDate(data.getExpiryDate());
                    fileRecords.add(fileModel);
                }
                logger.info("Exported data : [" + fileRecords + "]");
                csvWriter.write(fileRecords);
            } else {
                csvWriter.write(new QualifiedAgentsDataFileModel());
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
