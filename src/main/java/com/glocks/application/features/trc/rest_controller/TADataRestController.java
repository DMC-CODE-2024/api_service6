package com.glocks.application.features.trc.rest_controller;

import com.glocks.application.entity.app.TRCTypeApprovedDataEntity;
import com.glocks.application.features.trc.export.TRCApprovedDataExport;
import com.glocks.application.features.trc.paging.TRCApprovedDataPaging;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/trc/type-approved-data")
public class TADataRestController {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private final TRCApprovedDataPaging trcApprovedDataPaging;
    private final TRCApprovedDataExport trcApprovedDataExport;

    public TADataRestController(TRCApprovedDataPaging trcApprovedDataPaging, TRCApprovedDataExport trcApprovedDataExport) {
        this.trcApprovedDataPaging = trcApprovedDataPaging;
        this.trcApprovedDataExport = trcApprovedDataExport;
    }

    @Tag(name = "Type Approved", description = "Type Approval Module API")
    @Operation(
            summary = "Fetch all record",
            description = "Fetches all type approved entities from data source  based on trcApprovedStatus")

    @PostMapping("/paging")
    public MappingJacksonValue paging(@RequestBody TRCTypeApprovedDataEntity trcTypeApprovedDataEntity, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("TRCTypeApprovedDataEntity payload for paging [" + trcTypeApprovedDataEntity + "]");
        return new MappingJacksonValue(trcApprovedDataPaging.findAll(trcTypeApprovedDataEntity, pageNo, pageSize));
    }

    @Tag(name = "Type Approved", description = "Type Approval Module API")
    @Operation(
            summary = "Export csv file",
            description = "Fetches all type approved entities and their associated data from the data source, with the number of records limited to a configurable parameter, up to a maximum of 50,000. Subsequently, generate a .csv file containing the retrieved data.")

    @PostMapping("/export")
    public MappingJacksonValue export(@RequestBody TRCTypeApprovedDataEntity trcTypeApprovedDataEntity) {
        logger.info("TRCTypeApprovedDataEntity payload for export [" + trcTypeApprovedDataEntity + "]");
        return new MappingJacksonValue(trcApprovedDataExport.export(trcTypeApprovedDataEntity));
    }

}
