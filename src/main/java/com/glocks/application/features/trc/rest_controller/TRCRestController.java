package com.glocks.application.features.trc.rest_controller;

import com.glocks.application.common.model.ResponseModel;
import com.glocks.application.entity.app.TRCDataManagementEntity;
import com.glocks.application.features.trc.service.TRCExportService;
import com.glocks.application.features.trc.service.TRCUploadService;
import com.glocks.application.features.trc.service.TRCPagingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/v1/trc")
public class TRCRestController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final TRCPagingService trcPagingService;
    private final TRCExportService trcExportService;
    private final TRCUploadService trcUploadService;

    public TRCRestController(TRCPagingService trcPagingService, TRCExportService trcExportService, TRCUploadService trcUploadService) {
        this.trcPagingService = trcPagingService;
        this.trcExportService = trcExportService;
        this.trcUploadService = trcUploadService;
    }

    @PostMapping("/upload")
    public ResponseModel fileUpload(@Valid @RequestBody TRCDataManagementEntity trcDataManagementEntity) {
        logger.info("TRCDataManagementEntity payload for file upload [" + trcDataManagementEntity + "]");
        return trcUploadService.upload(trcDataManagementEntity);

    }

    @PostMapping("/paging")
    public MappingJacksonValue paging(@Valid @RequestBody TRCDataManagementEntity trcDataManagementEntity, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("payload [" + trcDataManagementEntity + "]");
        return trcPagingService.paging(trcDataManagementEntity, pageNo, pageSize);
    }

    @PostMapping("/export")
    public MappingJacksonValue export(@Valid @RequestBody TRCDataManagementEntity trcDataManagementEntity) {
        logger.info("export [" + trcDataManagementEntity + "]");
        return trcExportService.export(trcDataManagementEntity);
    }

    @PostMapping("/view")
    public ResponseEntity<?> get(@Valid @RequestBody TRCDataManagementEntity trcDataManagementEntity) {
        return new ResponseEntity<>(trcPagingService.find(trcDataManagementEntity), HttpStatus.OK);
    }

}

