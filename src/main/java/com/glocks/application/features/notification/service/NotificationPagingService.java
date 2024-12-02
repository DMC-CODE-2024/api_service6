package com.glocks.application.features.notification.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.entity.app.EIRSListManagementEntity;
import com.glocks.application.entity.app.NotificationEntity;
import com.glocks.application.features.notification.paging.NotificationPaging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import  jakarta.persistence.EntityManager;
import  jakarta.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class NotificationPagingService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;
    private NotificationPaging notificationPaging;

    private AuditTrailService auditTrailService;

    public NotificationPagingService(NotificationPaging notificationPaging, AuditTrailService auditTrailService) {
        this.notificationPaging = notificationPaging;
        this.auditTrailService = auditTrailService;
    }

    public MappingJacksonValue paging(NotificationEntity notificationEntity, int pageNo, int pageSize) {
        Page<NotificationEntity> page = notificationPaging.findAll(notificationEntity, pageNo, pageSize);
        String requestType = "NOTIFICATION_VIEWALL";
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(notificationEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
        return new MappingJacksonValue(page);
    }

    public MappingJacksonValue findById(NotificationEntity notificationEntity) {
        NotificationEntity result = notificationPaging.findById(notificationEntity.getId());
        String requestType = "NOTIFICATION";
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(notificationEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
        return new MappingJacksonValue(result);
    }
}
