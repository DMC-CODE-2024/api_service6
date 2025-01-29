package app.apiservice.features.listmanagement.rest_controller;

import app.apiservice.entity.app.GrayListEntity;
import app.apiservice.features.listmanagement.service.GrayListExportService;
import app.apiservice.features.listmanagement.service.GrayListPagingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Tag(name = "Tracked List Management", description = "List Management Module API")
    @Operation(
            summary = "Fetch all record",
            description = "Fetches all grey list entities and their data from data source")
    @PostMapping("/paging")
    public MappingJacksonValue paging(@RequestBody GrayListEntity grayListEntity, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("GrayListEntity payload for paging [" + grayListEntity + "]");
        return grayListPagingService.paging(grayListEntity, pageNo, pageSize);
    }

    @Tag(name = "Tracked List Management", description = "List Management Module API")
    @Operation(
            summary = "Export csv file",
            description = "Fetches all grey list entities and their associated data from the data source, with the number of records limited to a configurable parameter, up to a maximum of 50,000. Subsequently, generate a .csv file containing the retrieved data.")
    @PostMapping("/export")
    public MappingJacksonValue export(@RequestBody GrayListEntity grayListEntity) {
        logger.info("export payload [" + grayListEntity + "]");
        return grayListExportService.export(grayListEntity);
    }


}
