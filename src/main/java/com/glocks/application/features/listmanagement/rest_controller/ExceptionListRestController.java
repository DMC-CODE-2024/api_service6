package com.glocks.application.features.listmanagement.rest_controller;

import com.glocks.application.common.model.ResponseModel;
import com.glocks.application.entity.app.EIRSListManagementEntity;
import com.glocks.application.entity.app.ExceptionListEntity;
import com.glocks.application.features.listmanagement.service.EIRSListManagementPagingService;
import com.glocks.application.features.listmanagement.service.ExceptionListExportService;
import com.glocks.application.features.listmanagement.service.ExceptionListPagingService;
import com.glocks.application.features.listmanagement.service.FileUploadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/list-mgmt/exception-list")
public class ExceptionListRestController {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private ExceptionListPagingService exceptionListPagingService;
    private ExceptionListExportService exceptionListExportService;

    public ExceptionListRestController(ExceptionListPagingService exceptionListPagingService, ExceptionListExportService exceptionListExportService) {
        this.exceptionListPagingService = exceptionListPagingService;
        this.exceptionListExportService = exceptionListExportService;
    }

    @PostMapping("/paging")
    public MappingJacksonValue paging(@RequestBody ExceptionListEntity exceptionListEntity, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("ExceptionListEntity payload for paging [" + exceptionListEntity + "]");
        return exceptionListPagingService.paging(exceptionListEntity, pageNo, pageSize);
    }

    @PostMapping("/export")
    public MappingJacksonValue export(@RequestBody ExceptionListEntity exceptionListEntity) {
        logger.info("export payload [" + exceptionListEntity + "]");
        return exceptionListExportService.export(exceptionListEntity);
    }


}
