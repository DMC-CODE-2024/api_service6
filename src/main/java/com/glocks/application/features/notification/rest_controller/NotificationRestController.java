package com.glocks.application.features.notification.rest_controller;

import com.glocks.application.common.model.ResponseModel;
import com.glocks.application.entity.app.EIRSListManagementEntity;
import com.glocks.application.entity.app.NotificationEntity;
import com.glocks.application.features.notification.service.NotificationExportService;
import com.glocks.application.features.notification.service.NotificationPagingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/notification")
public class NotificationRestController {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private NotificationPagingService notificationPagingService;
    private NotificationExportService notificationExportService;

    public NotificationRestController(NotificationPagingService notificationPagingService, NotificationExportService notificationExportService) {
        this.notificationPagingService = notificationPagingService;
        this.notificationExportService = notificationExportService;
    }


    @PostMapping("/paging")
    public MappingJacksonValue paging(@RequestBody NotificationEntity notificationEntity, @RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("NotificationEntity payload for paging [" + notificationEntity + "]");
        return notificationPagingService.paging(notificationEntity, pageNo, pageSize);
    }


    @PostMapping("/export")
    public MappingJacksonValue export(@RequestBody NotificationEntity notificationEntity) {
        logger.info("NotificationEntity payload for export [" + notificationEntity + "]");
        return notificationExportService.export(notificationEntity);
    }

    @PostMapping
    public MappingJacksonValue findById(@RequestBody NotificationEntity notificationEntity) {
        return notificationPagingService.findById(notificationEntity);
    }
}
