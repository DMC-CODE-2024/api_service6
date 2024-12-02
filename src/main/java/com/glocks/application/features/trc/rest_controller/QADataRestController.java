package com.glocks.application.features.trc.rest_controller;

import com.glocks.application.entity.app.TRCQualifiedAgentsDataEntity;
import com.glocks.application.entity.app.TRCTypeApprovedDataEntity;
import com.glocks.application.features.trc.service.QualifiedAgentsDataExportService;
import com.glocks.application.features.trc.service.QualifiedAgentsDataPagingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/trc/qualified-agents-data")
public class QADataRestController {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final QualifiedAgentsDataPagingService qualifiedAgentsDataPagingService;
    private final QualifiedAgentsDataExportService qualifiedAgentsDataExportService;

    public QADataRestController(QualifiedAgentsDataPagingService qualifiedAgentsDataPagingService, QualifiedAgentsDataExportService qualifiedAgentsDataExportService) {
        this.qualifiedAgentsDataPagingService = qualifiedAgentsDataPagingService;
        this.qualifiedAgentsDataExportService = qualifiedAgentsDataExportService;
    }

    @PostMapping("/paging")
    public MappingJacksonValue paging(@RequestBody TRCQualifiedAgentsDataEntity trcQualifiedAgentsDataEntity, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("TRCQualifiedAgentsDataEntity payload for QA Data [" + trcQualifiedAgentsDataEntity + "]");
        return qualifiedAgentsDataPagingService.paging(trcQualifiedAgentsDataEntity, pageNo, pageSize);
    }

    @PostMapping("/export")
    public MappingJacksonValue export(@RequestBody TRCQualifiedAgentsDataEntity trcQualifiedAgentsDataEntity) {
        logger.info("TRCQualifiedAgentsDataEntity payload for export [" + trcQualifiedAgentsDataEntity + "]");
        return qualifiedAgentsDataExportService.export(trcQualifiedAgentsDataEntity);
    }

}
