package com.glocks.application.features.listmanagement.rest_controller;

import com.glocks.application.common.model.ResponseModel;
import com.glocks.application.entity.app.EIRSListManagementEntity;
import com.glocks.application.features.listmanagement.service.EIRSListManagementExportService;
import com.glocks.application.features.listmanagement.service.EIRSListManagementPagingService;
import com.glocks.application.features.listmanagement.service.FileUploadService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/list-mgmt/eirs-list-mgmt")
public class EIRSListManagementRestController {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private FileUploadService fileUploadService;
    private EIRSListManagementPagingService eirsListManagementPagingService;
    private EIRSListManagementExportService eirsListManagementExport;

    public EIRSListManagementRestController(FileUploadService fileUploadService, EIRSListManagementPagingService eirsListManagementPagingService, EIRSListManagementExportService eirsListManagementExport) {
        this.fileUploadService = fileUploadService;
        this.eirsListManagementPagingService = eirsListManagementPagingService;
        this.eirsListManagementExport = eirsListManagementExport;
    }

    @PostMapping("/upload")
    public ResponseModel fileUpload(@Valid @RequestBody EIRSListManagementEntity eirsListManagementEntity) {
        logger.info("EIRSListManagementEntity payload for file upload [" + eirsListManagementEntity + "]");
        return fileUploadService.save(eirsListManagementEntity);

    }


    @PostMapping("/paging")
    public MappingJacksonValue paging(@RequestBody EIRSListManagementEntity eirsListManagementEntity, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("EIRSListManagementEntity payload for paging [" + eirsListManagementEntity + "]");
        return eirsListManagementPagingService.paging(eirsListManagementEntity, pageNo, pageSize);
    }

    @PostMapping
    public MappingJacksonValue findByID(@RequestBody EIRSListManagementEntity eirsListManagementEntity) {
        return new MappingJacksonValue(eirsListManagementPagingService.find(eirsListManagementEntity));
    }

    @PostMapping("/export")
    public MappingJacksonValue export(@RequestBody EIRSListManagementEntity eirsListManagementEntity) {
        logger.info("EIRSListManagementEntity payload for export [" + eirsListManagementEntity + "]");
        return eirsListManagementExport.export(eirsListManagementEntity);
    }


}
