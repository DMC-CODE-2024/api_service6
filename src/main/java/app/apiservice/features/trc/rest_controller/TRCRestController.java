package app.apiservice.features.trc.rest_controller;

import app.apiservice.common.model.ResponseModel;
import app.apiservice.entity.app.TRCDataManagementEntity;
import app.apiservice.features.trc.service.TRCExportService;
import app.apiservice.features.trc.service.TRCPagingService;
import app.apiservice.features.trc.service.TRCUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/trc")
public class TRCRestController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final TRCPagingService trcPagingService;
    private final TRCExportService trcExportService;
    private final TRCUploadService trcUploadService;

    public TRCRestController(TRCPagingService trcPagingService, TRCExportService trcExportService, TRCUploadService trcUploadService) {
        this.trcPagingService = trcPagingService;
        this.trcExportService = trcExportService;
        this.trcUploadService = trcUploadService;
    }

    @Tag(name = "Type Approval", description = "Type Approval Module API")
    @Operation(summary = "Save type approval record", description = "Save type approval data into the data source")
    @PostMapping("/upload")
    public ResponseModel fileUpload(@Valid @RequestBody TRCDataManagementEntity trcDataManagementEntity) {
        logger.info("TRCDataManagementEntity payload for file upload [" + trcDataManagementEntity + "]");
        return trcUploadService.upload(trcDataManagementEntity);

    }

    @Tag(name = "Type Approval", description = "Type Approval Module API")
    @Operation(summary = "Fetch all record based on requestType", description = "Fetches all type Approval entities and their data from data source  based on requestType(TA/QA) ")

    @PostMapping("/paging")
    public MappingJacksonValue paging(@Valid @RequestBody TRCDataManagementEntity trcDataManagementEntity, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("payload [" + trcDataManagementEntity + "]");
        return trcPagingService.paging(trcDataManagementEntity, pageNo, pageSize);
    }

    @Tag(name = "Type Approval", description = "Type Approval Module API")
    @Operation(summary = "Export csv file", description = "Fetches all type Approval entities and their associated data from the data source, with the number of records limited to a configurable parameter, up to a maximum of 50,000. Subsequently, generate a .csv file containing the retrieved data.")

    @PostMapping("/export")
    public MappingJacksonValue export(@Valid @RequestBody TRCDataManagementEntity trcDataManagementEntity) {
        logger.info("export [" + trcDataManagementEntity + "]");
        return trcExportService.export(trcDataManagementEntity);
    }

    @Tag(name = "Type Approval", description = "Type Approval Module API")
    @Operation(summary = "Fetch single record based on Id", description = "Fetches record based on Id from data source")
    @PostMapping("/view")
    public ResponseEntity<?> get(@Valid @RequestBody TRCDataManagementEntity trcDataManagementEntity) {
        return new ResponseEntity<>(trcPagingService.find(trcDataManagementEntity), HttpStatus.OK);
    }

}

