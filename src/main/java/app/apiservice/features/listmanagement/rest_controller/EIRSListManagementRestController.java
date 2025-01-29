package app.apiservice.features.listmanagement.rest_controller;

import app.apiservice.common.model.ResponseModel;
import app.apiservice.entity.app.EIRSListManagementEntity;
import app.apiservice.features.listmanagement.service.EIRSListManagementExportService;
import app.apiservice.features.listmanagement.service.EIRSListManagementPagingService;
import app.apiservice.features.listmanagement.service.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/list-mgmt/eirs-list-mgmt")
public class EIRSListManagementRestController {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private FileUploadService fileUploadService;
    private EIRSListManagementPagingService eirsListManagementPagingService;
    private EIRSListManagementExportService eirsListManagementExport;

    public EIRSListManagementRestController(FileUploadService fileUploadService, EIRSListManagementPagingService eirsListManagementPagingService, EIRSListManagementExportService eirsListManagementExport) {
        this.fileUploadService = fileUploadService;
        this.eirsListManagementPagingService = eirsListManagementPagingService;
        this.eirsListManagementExport = eirsListManagementExport;
    }

    @Tag(name = "List Management", description = "List Management Module API")
    @Operation(
            summary = "Save list management record",
            description = "Save list management data into the data source")
    @PostMapping("/upload")
    public ResponseModel fileUpload(@Valid @RequestBody EIRSListManagementEntity eirsListManagementEntity) {
        logger.info("EIRSListManagementEntity payload for file upload [" + eirsListManagementEntity + "]");
        return fileUploadService.save(eirsListManagementEntity);

    }

    @Tag(name = "List Management", description = "List Management Module API")
    @Operation(
            summary = "Fetch all record based on requestType",
            description = "Fetches all list management entities and their data from data source  based on requestType(EXCEPTION_LIST/BLOCK_LIST/BLOCK_TAC) ")
    @PostMapping("/paging")
    public MappingJacksonValue paging(
            @RequestBody EIRSListManagementEntity eirsListManagementEntity, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("EIRSListManagementEntity payload for paging [" + eirsListManagementEntity + "]");
        return eirsListManagementPagingService.paging(eirsListManagementEntity, pageNo, pageSize);
    }

    @Tag(name = "List Management", description = "List Management Module API")
    @Operation(
            summary = "Fetch single record based on Id",
            description = "Fetches record based on Id from data source")
    @PostMapping
    public MappingJacksonValue findByID(@RequestBody EIRSListManagementEntity eirsListManagementEntity) {
        return new MappingJacksonValue(eirsListManagementPagingService.find(eirsListManagementEntity));
    }

    @Tag(name = "List Management", description = "List Management Module API")
    @Operation(
            summary = "Export csv file",
            description = "Fetches all list management entities and their associated data from the data source, with the number of records limited to a configurable parameter, up to a maximum of 50,000. Subsequently, generate a .csv file containing the retrieved data.")
    @PostMapping("/export")
    public MappingJacksonValue export(@RequestBody EIRSListManagementEntity eirsListManagementEntity) {
        logger.info("EIRSListManagementEntity payload for export [" + eirsListManagementEntity + "]");
        return eirsListManagementExport.export(eirsListManagementEntity);
    }


}
