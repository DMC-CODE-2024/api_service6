package com.glocks.application.features.trc.export;

import com.glocks.application.common.CustomMappingStrategy;
import com.glocks.application.common.db.PropertiesReader;
import com.glocks.application.common.exceptions.ResourceServicesException;
import com.glocks.application.common.model.FileDetails;
import com.glocks.application.entity.app.SystemConfigurationDb;
import com.glocks.application.entity.app.TRCLocalManufacturedDevicesDumpEntity;
import com.glocks.application.features.trc.csv_file_model.LMDataFileModel;
import com.glocks.application.features.trc.paging.LMDataPaging;
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
public class LMDataExport {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private ExportService exportService;
    private LMDataPaging lmDataPaging;

    public LMDataExport(ExportService exportService, LMDataPaging lmDataPaging) {
        this.exportService = exportService;
        this.lmDataPaging = lmDataPaging;
    }

    @Autowired
    private PropertiesReader propertiesReader;

    public FileDetails export(TRCLocalManufacturedDevicesDumpEntity trcLocalManufacturedDevicesDumpEntity, String exportFileName) {
        String fileName = null;
        Writer writer = null;
        int pageNo = 0;
        int pageSize = exportService.pageSize();
        LMDataFileModel fileModel = null;
        DateTimeFormatter dtf = exportService.forDate();
        DateTimeFormatter dtf2 = exportService.forFile();
        SystemConfigurationDb filepath = exportService.filepath();
        SystemConfigurationDb link = exportService.link();
        logger.info("File Path :  [" + filepath + "] & Link : [" + link + "]");
        StatefulBeanToCsvBuilder<LMDataFileModel> builder = null;
        StatefulBeanToCsv<LMDataFileModel> csvWriter = null;
        List<LMDataFileModel> fileRecords = null;
        CustomMappingStrategy<LMDataFileModel> mappingStrategy = new CustomMappingStrategy<>();

        try {
            List<TRCLocalManufacturedDevicesDumpEntity> list = lmDataPaging.findAll(trcLocalManufacturedDevicesDumpEntity, pageNo, pageSize).getContent();
            fileName = LocalDateTime.now().format(dtf2).replace(" ", "_") + "_" + exportFileName + ".csv";
            writer = Files.newBufferedWriter(Paths.get(filepath.getValue() + fileName));
            mappingStrategy.setType(LMDataFileModel.class);

            builder = new StatefulBeanToCsvBuilder<>(writer);
            csvWriter = builder.withMappingStrategy(mappingStrategy).withSeparator(',').withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();

            if (!list.isEmpty()) {
                fileRecords = new ArrayList<LMDataFileModel>();
                for (TRCLocalManufacturedDevicesDumpEntity data : list) {
                    fileModel = new LMDataFileModel().setUploadedDate(data.getCreatedOn().format(dtf)).setSerialNumber(data.getSerialNumber()).setImei(data.getActualImei()).setManufactureID(data.getManufacturerId()).setManufactureName(data.getManufacturerName());
                    fileRecords.add(fileModel);
                }
                logger.info("Exported data : [" + fileRecords + "]");
                csvWriter.write(fileRecords);
            } else {
                csvWriter.write(new LMDataFileModel());
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
