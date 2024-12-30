package com.glocks.application.features.notification.export;

import com.glocks.application.common.CustomMappingStrategy;
import com.glocks.application.common.db.PropertiesReader;
import com.glocks.application.common.exceptions.ResourceServicesException;
import com.glocks.application.common.features.sysparam.ConfigTags;
import com.glocks.application.common.features.sysparam.ConfigurationManagementServiceImpl;
import com.glocks.application.common.model.FileDetails;
import com.glocks.application.entity.app.NotificationEntity;
import com.glocks.application.entity.app.SystemConfigurationDb;
import com.glocks.application.features.notification.csv_file_model.NotificationFileModel;
import com.glocks.application.features.notification.paging.NotificationPaging;
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

@Component
public class NotificationExport {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private ConfigurationManagementServiceImpl configurationManagementServiceImpl;
    private NotificationPaging notificationPaging;

    public NotificationExport(ConfigurationManagementServiceImpl configurationManagementServiceImpl, NotificationPaging notificationPaging) {
        this.configurationManagementServiceImpl = configurationManagementServiceImpl;
        this.notificationPaging = notificationPaging;
    }

    @Autowired
    private PropertiesReader propertiesReader;

    public FileDetails export(NotificationEntity notificationEntity, String subFeature) {
        String fileName = null;
        Writer writer = null;
        Integer pageNo = 0;
        Integer pageSize = Integer.valueOf(configurationManagementServiceImpl.findByTag("file.max-file-record").getValue());

        NotificationFileModel fileModel = null;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

        SystemConfigurationDb filepath = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_dir);
        SystemConfigurationDb link = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_link);
        logger.info("File Path :  [" + filepath + "] & Link : [" + link + "]");

        StatefulBeanToCsvBuilder<NotificationFileModel> builder = null;
        StatefulBeanToCsv<NotificationFileModel> csvWriter = null;
        List<NotificationFileModel> fileRecords = null;
        CustomMappingStrategy<NotificationFileModel> mappingStrategy = new CustomMappingStrategy<>();

        try {
            List<NotificationEntity> list = notificationPaging.findAll(notificationEntity, pageNo, pageSize).getContent();
            fileName = LocalDateTime.now().format(dtf2).replace(" ", "_") + "_" + subFeature + ".csv";
            writer = Files.newBufferedWriter(Paths.get(filepath.getValue() + fileName));
            mappingStrategy.setType(NotificationFileModel.class);

            builder = new StatefulBeanToCsvBuilder<>(writer);
            csvWriter = builder.withMappingStrategy(mappingStrategy).withSeparator(',').withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();

            if (list.size() > 0) {
                fileRecords = new ArrayList<NotificationFileModel>();
                for (NotificationEntity data : list) {
                    fileModel = new NotificationFileModel()
                            .setCreationDate(data.getCreatedOn().format(dtf))
                            .setChannelType(data.getChannelType())
                            .setFeatureName(data.getFeatureName())
                            .setFeatureTxnId(data.getFeatureTxnId())
                            .setMsisdn(data.getMsisdn())
                            .setEmail(data.getEmail());
                    fileRecords.add(fileModel);
                }
                logger.info("Exported data : [" + fileRecords + "]");
                csvWriter.write(fileRecords);
            } else {
                csvWriter.write(new NotificationFileModel());
            }
            logger.info("fileName [" + fileName + "] filePath [" + filepath + "] download link [" + link.getValue() + "]");

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
