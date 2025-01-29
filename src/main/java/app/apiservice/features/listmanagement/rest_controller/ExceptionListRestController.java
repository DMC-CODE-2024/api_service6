package app.apiservice.features.listmanagement.rest_controller;

import app.apiservice.entity.app.ExceptionListEntity;
import app.apiservice.features.listmanagement.service.ExceptionListExportService;
import app.apiservice.features.listmanagement.service.ExceptionListPagingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

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

    @Tag(name = "Exception List", description = "List Management Module API")
    @Operation(
            summary = "Fetch all record",
            description = "Fetches all exception list entities and their data from data source")
    @PostMapping("/paging")
    public MappingJacksonValue paging(@RequestBody ExceptionListEntity exceptionListEntity, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("ExceptionListEntity payload for paging [" + exceptionListEntity + "]");
        return exceptionListPagingService.paging(exceptionListEntity, pageNo, pageSize);
    }

    @Tag(name = "Exception List", description = "List Management Module API")
    @Operation(
            summary = "Export csv file",
            description = "Fetches all exception list entities and their associated data from the data source, with the number of records limited to a configurable parameter, up to a maximum of 50,000. Subsequently, generate a .csv file containing the retrieved data.")

    @PostMapping("/export")
    public MappingJacksonValue export(@RequestBody ExceptionListEntity exceptionListEntity) {
        logger.info("export payload [" + exceptionListEntity + "]");
        return exceptionListExportService.export(exceptionListEntity);
    }


}
