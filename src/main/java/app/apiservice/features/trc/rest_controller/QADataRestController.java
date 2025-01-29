package app.apiservice.features.trc.rest_controller;

import app.apiservice.entity.app.TRCQualifiedAgentsDataEntity;
import app.apiservice.features.trc.service.QualifiedAgentsDataExportService;
import app.apiservice.features.trc.service.QualifiedAgentsDataPagingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/trc/qualified-agents-data")
public class QADataRestController {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final QualifiedAgentsDataPagingService qualifiedAgentsDataPagingService;
    private final QualifiedAgentsDataExportService qualifiedAgentsDataExportService;

    public QADataRestController(QualifiedAgentsDataPagingService qualifiedAgentsDataPagingService, QualifiedAgentsDataExportService qualifiedAgentsDataExportService) {
        this.qualifiedAgentsDataPagingService = qualifiedAgentsDataPagingService;
        this.qualifiedAgentsDataExportService = qualifiedAgentsDataExportService;
    }

    @Tag(name = "Qualified Agent", description = "Type Approval Module API")
    @Operation(
            summary = "Fetch all record",
            description = "Fetches all qualified agent entities from data source  based on trcApprovedStatus")

    @PostMapping("/paging")
    public MappingJacksonValue paging(@RequestBody TRCQualifiedAgentsDataEntity trcQualifiedAgentsDataEntity, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("TRCQualifiedAgentsDataEntity payload for QA Data [" + trcQualifiedAgentsDataEntity + "]");
        return qualifiedAgentsDataPagingService.paging(trcQualifiedAgentsDataEntity, pageNo, pageSize);
    }

    @Tag(name = "Qualified Agent", description = "Type Approval Module API")
    @Operation(
            summary = "Export csv file",
            description = "Fetches all qualified agent entities and their associated data from the data source, with the number of records limited to a configurable parameter, up to a maximum of 50,000. Subsequently, generate a .csv file containing the retrieved data.")

    @PostMapping("/export")
    public MappingJacksonValue export(@RequestBody TRCQualifiedAgentsDataEntity trcQualifiedAgentsDataEntity) {
        logger.info("TRCQualifiedAgentsDataEntity payload for export [" + trcQualifiedAgentsDataEntity + "]");
        return qualifiedAgentsDataExportService.export(trcQualifiedAgentsDataEntity);
    }

}
