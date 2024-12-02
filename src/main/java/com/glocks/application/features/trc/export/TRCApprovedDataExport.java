package com.glocks.application.features.trc.export;

import com.glocks.application.common.CustomMappingStrategy;
import com.glocks.application.common.db.PropertiesReader;
import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.exceptions.ResourceServicesException;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.common.features.sysparam.ConfigTags;
import com.glocks.application.common.features.sysparam.ConfigurationManagementServiceImpl;
import com.glocks.application.common.model.FileDetails;
import com.glocks.application.entity.app.SystemConfigurationDb;
import com.glocks.application.entity.app.TRCTypeApprovedDataEntity;
import com.glocks.application.features.trc.csv_file_model.TRCApprovedDataFileModel;
import com.glocks.application.features.trc.paging.TRCApprovedDataPaging;
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
public class TRCApprovedDataExport {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private ConfigurationManagementServiceImpl configurationManagementServiceImpl;
    private TRCApprovedDataPaging trcApprovedDataPaging;
    private AuditTrailService auditTrailService;

    public TRCApprovedDataExport(ConfigurationManagementServiceImpl configurationManagementServiceImpl, TRCApprovedDataPaging trcApprovedDataPaging, AuditTrailService auditTrailService) {
        this.configurationManagementServiceImpl = configurationManagementServiceImpl;
        this.trcApprovedDataPaging = trcApprovedDataPaging;
        this.auditTrailService = auditTrailService;
    }

    @Autowired
    private PropertiesReader propertiesReader;

    public FileDetails export(TRCTypeApprovedDataEntity trcTypeApprovedDataEntity) {

        String fileName = null;
        Writer writer = null;
        int pageNo = 0;
        int pageSize = Integer.parseInt(configurationManagementServiceImpl.findByTag("file.max-file-record").getValue());

        TRCApprovedDataFileModel fileModel = null;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

        SystemConfigurationDb filepath = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_dir);
        SystemConfigurationDb link = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_link);
        logger.info("File Path :  [" + filepath + "] & Link : [" + link + "]");

        StatefulBeanToCsvBuilder<TRCApprovedDataFileModel> builder = null;
        StatefulBeanToCsv<TRCApprovedDataFileModel> csvWriter = null;
        List<TRCApprovedDataFileModel> fileRecords = null;
        CustomMappingStrategy<TRCApprovedDataFileModel> mappingStrategy = new CustomMappingStrategy<>();
        FileDetails fileDetails;
        try {
            List<TRCTypeApprovedDataEntity> list = trcApprovedDataPaging.findAll(trcTypeApprovedDataEntity, pageNo, pageSize).getContent();
            fileName = LocalDateTime.now().format(dtf2).replace(" ", "_") + "_TAData.csv";
            writer = Files.newBufferedWriter(Paths.get(filepath.getValue() + fileName));
            mappingStrategy.setType(TRCApprovedDataFileModel.class);

            builder = new StatefulBeanToCsvBuilder<>(writer);
            csvWriter = builder.withMappingStrategy(mappingStrategy).withSeparator(',').withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();

            if (!list.isEmpty()) {
                fileRecords = new ArrayList<TRCApprovedDataFileModel>();
                for (TRCTypeApprovedDataEntity data : list) {
                    fileModel = new TRCApprovedDataFileModel()
                            .setApprovedDate(data.getApprovalDate())
                            .setCompany(data.getCompany())
                            .setCompanyId(data.getCompanyId())
                            .setTrademark(data.getTrademark())
                            .setProductName(data.getProductName())
                            .setCommercialName(data.getCommercialName())
                            .setModel(data.getModel())
                            .setCountry(data.getCountry())
                            .setTrcIdentifier(data.getTrcIdentifier());
                    fileRecords.add(fileModel);

                }
                logger.info("Exported data : [" + fileRecords + "]");
                csvWriter.write(fileRecords);
            } else {
                csvWriter.write(new TRCApprovedDataFileModel());
            }
            logger.info("fileName [" + fileName + "] filePath [" + filepath + "] download link [" + link.getValue() + "]");

            fileDetails = new FileDetails(fileName, filepath.getValue(), link.getValue().replace("$LOCAL_IP", propertiesReader.localIp) + fileName);
            logger.info("export file Details [" + fileDetails + "]");
            auditTrailService.auditTrailOperation(trcTypeApprovedDataEntity.getAuditTrailModel(), FeaturesEnum.TA_EXPORT.getFeature(), FeaturesEnum.TA_EXPORT.getSubFeature());


        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
        } finally {
            try {

                if (writer != null) writer.close();
            } catch (IOException e) {
            }
        }
        return fileDetails;
    }
}
