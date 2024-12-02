package com.glocks.application.features.listmanagement.rest_controller;

import com.glocks.application.entity.app.GrayListEntity;
import com.glocks.application.features.listmanagement.service.GrayListExportService;
import com.glocks.application.features.listmanagement.service.GrayListPagingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/list-mgmt/gray-list")
public class GrayListRestController {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private GrayListPagingService grayListPagingService;
    private GrayListExportService grayListExportService;

    public GrayListRestController(GrayListPagingService grayListPagingService, GrayListExportService grayListExportService) {
        this.grayListPagingService = grayListPagingService;
        this.grayListExportService = grayListExportService;
    }

    @PostMapping("/paging")
    public MappingJacksonValue paging(@RequestBody GrayListEntity grayListEntity, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("GrayListEntity payload for paging [" + grayListEntity + "]");
        return grayListPagingService.paging(grayListEntity, pageNo, pageSize);
    }

    @PostMapping("/export")
    public MappingJacksonValue export(@RequestBody GrayListEntity grayListEntity) {
        logger.info("export payload [" + grayListEntity + "]");
        return grayListExportService.export(grayListEntity);
    }


}
