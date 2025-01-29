package app.apiservice.features.notification.rest_controller;

import app.apiservice.entity.app.NotificationEntity;
import app.apiservice.features.notification.service.NotificationExportService;
import app.apiservice.features.notification.service.NotificationPagingService;
import app.apiservice.features.trc.service.DistinctParamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/notification")
public class NotificationRestController {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private NotificationPagingService notificationPagingService;
    private NotificationExportService notificationExportService;
    private DistinctParamService distinctParamService;

    public NotificationRestController(NotificationPagingService notificationPagingService, NotificationExportService notificationExportService, DistinctParamService distinctParamService) {
        this.notificationPagingService = notificationPagingService;
        this.notificationExportService = notificationExportService;
        this.distinctParamService = distinctParamService;
    }


    @Tag(name = "View Notification", description = "Customer Care Module API")
    @Operation(
            summary = "Fetch all record",
            description = "Fetches all notification entities and their data from data source")

    @PostMapping("/paging")
    public MappingJacksonValue paging(@RequestBody NotificationEntity notificationEntity, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("NotificationEntity payload for paging [" + notificationEntity + "]");
        return notificationPagingService.paging(notificationEntity, pageNo, pageSize);
    }



    @Tag(name = "View Notification", description = "Customer Care Module API")
    @Operation(
            summary = "Export csv file",
            description = "Fetches all notification entities and their associated data from the data source, with the number of records limited to a configurable parameter, up to a maximum of 50,000. Subsequently, generate a .csv file containing the retrieved data.")

    @PostMapping("/export")
    public MappingJacksonValue export(@RequestBody NotificationEntity notificationEntity) {
        logger.info("NotificationEntity payload for export [" + notificationEntity + "]");
        return notificationExportService.export(notificationEntity);
    }

    @Tag(name = "View Notification", description = "Customer Care Module API")
    @Operation(
            summary = "Fetch single record based on Id",
            description = "Fetches record based on Id from data source")
    @PostMapping
    public MappingJacksonValue findById(@RequestBody NotificationEntity notificationEntity) {
        return notificationPagingService.findById(notificationEntity);
    }

    @Tag(name = "View Notification", description = "Customer Care Module API")
    @Operation(
            summary = "Fetch distinct value from the data source",
            description = "Fetch distinct values for the notification based on the received request")
    @PostMapping("/distinct")
    public ResponseEntity<?> distinct(@RequestBody List<String> param) {
        Class<NotificationEntity> entity = NotificationEntity.class;
        return new ResponseEntity<>(distinctParamService.distinct(param, entity), HttpStatus.OK);
    }
}
