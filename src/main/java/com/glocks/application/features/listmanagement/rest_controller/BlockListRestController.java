package com.glocks.application.features.listmanagement.rest_controller;

import com.glocks.application.entity.app.BlockListEntity;
import com.glocks.application.features.listmanagement.service.BlockListExportService;
import com.glocks.application.features.listmanagement.service.BlockListPagingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/list-mgmt/block-list")
public class BlockListRestController {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private BlockListPagingService blockListPagingService;
    private BlockListExportService blockListExportService;

    public BlockListRestController(BlockListPagingService blockListPagingService, BlockListExportService blockListExportService) {
        this.blockListPagingService = blockListPagingService;
        this.blockListExportService = blockListExportService;
    }

    @PostMapping("/paging")
    public MappingJacksonValue paging(@RequestBody BlockListEntity blockListEntity, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("BlockListEntity payload for paging [" + blockListEntity + "]");
        return blockListPagingService.paging(blockListEntity, pageNo, pageSize);
    }

    @PostMapping("/export")
    public MappingJacksonValue export(@RequestBody BlockListEntity blockListEntity) {
        logger.info("export payload [" + blockListEntity + "]");
        return blockListExportService.export(blockListEntity);
    }


}
