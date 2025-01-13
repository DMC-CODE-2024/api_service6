package com.glocks.application.features.listmanagement.rest_controller;

import com.glocks.application.entity.app.BlockTACListEntity;
import com.glocks.application.features.listmanagement.service.BlockTACExportService;
import com.glocks.application.features.listmanagement.service.BlockTACPagingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Tag(name = "Blocked TAC", description = "List Management Module API")
    @Operation(
            summary = "Fetch all record",
            description = "Fetches all blocked tac list entities and their data from data source")

    @PostMapping("/paging")
    public MappingJacksonValue paging(@RequestBody BlockTACListEntity blockTACListEntity, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("BlockListEntity payload for paging [" + blockTACListEntity + "]");
        return blockTACPagingService.paging(blockTACListEntity, pageNo, pageSize);
    }


    @Tag(name = "Blocked TAC", description = "List Management Module API")
    @Operation(
            summary = "Export csv file",
            description = "Fetches all blocked tac list entities and their associated data from the data source, with the number of records limited to a configurable parameter, up to a maximum of 50,000. Subsequently, generate a .csv file containing the retrieved data.")

    @PostMapping("/export")
    public MappingJacksonValue export(@RequestBody BlockTACListEntity blockTACListEntity) {
        logger.info("export payload [" + blockTACListEntity + "]");
        return blockTACExportService.export(blockTACListEntity);
    }


}
