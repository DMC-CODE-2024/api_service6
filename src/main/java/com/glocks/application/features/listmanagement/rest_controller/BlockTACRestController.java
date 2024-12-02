package com.glocks.application.features.listmanagement.rest_controller;

import com.glocks.application.entity.app.BlockTACListEntity;
import com.glocks.application.features.listmanagement.service.BlockTACExportService;
import com.glocks.application.features.listmanagement.service.BlockTACPagingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/list-mgmt/block-tac")
public class BlockTACRestController {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private BlockTACPagingService blockTACPagingService;
    private BlockTACExportService blockTACExportService;

    public BlockTACRestController(BlockTACPagingService blockTACPagingService, BlockTACExportService blockTACExportService) {
        this.blockTACPagingService = blockTACPagingService;
        this.blockTACExportService = blockTACExportService;
    }

    @PostMapping("/paging")
    public MappingJacksonValue paging(@RequestBody BlockTACListEntity blockTACListEntity, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("BlockListEntity payload for paging [" + blockTACListEntity + "]");
        return blockTACPagingService.paging(blockTACListEntity, pageNo, pageSize);
    }

    @PostMapping("/export")
    public MappingJacksonValue export(@RequestBody BlockTACListEntity blockTACListEntity) {
        logger.info("export payload [" + blockTACListEntity + "]");
        return blockTACExportService.export(blockTACListEntity);
    }


}
