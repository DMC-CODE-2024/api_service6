package app.apiservice.features.trc.rest_controller;

import app.apiservice.entity.app.TRCLocalManufacturedDevicesDumpEntity;
import app.apiservice.features.trc.service.LMDataPagingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/trc/lm-data")
public class LMDataRestController {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private final LMDataPagingService lmDataPagingService;

    public LMDataRestController(LMDataPagingService lmDataPagingService) {
        this.lmDataPagingService = lmDataPagingService;
    }
    @Tag(name = "Local Manufacturer IMEI", description = "Type Approval Module API")
    @Operation(
            summary = "Fetch all record",
            description = "Fetches all local manufactured device entities from data source  based on trcApprovedStatus")

    @PostMapping("/paging")
    public MappingJacksonValue paging(@RequestBody TRCLocalManufacturedDevicesDumpEntity trcLocalManufacturedDevicesDumpEntity, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("TRCLocalManufacturedDevicesDumpEntity payload for paging [" + trcLocalManufacturedDevicesDumpEntity + "]");
        return lmDataPagingService.paging(trcLocalManufacturedDevicesDumpEntity, pageNo, pageSize);
    }

    @Tag(name = "Local Manufacturer IMEI", description = "Type Approval Module API")
    @Operation(
            summary = "Export csv file",
            description = "Fetches all local manufactured device entities and their associated data from the data source, with the number of records limited to a configurable parameter, up to a maximum of 50,000. Subsequently, generate a .csv file containing the retrieved data.")

    @PostMapping("/export")
    public MappingJacksonValue export(@RequestBody TRCLocalManufacturedDevicesDumpEntity trcLocalManufacturedDevicesDumpEntity) {
        logger.info("TRCLocalManufacuredDevicesDumpEntity payload for export [" + trcLocalManufacturedDevicesDumpEntity + "]");
        return lmDataPagingService.export(trcLocalManufacturedDevicesDumpEntity);
    }

}
