package com.glocks.application.features.notification.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.common.model.FileDetails;
import com.glocks.application.common.model.ResponseModel;
import com.glocks.application.entity.app.EIRSListManagementEntity;
import com.glocks.application.entity.app.NotificationEntity;
import com.glocks.application.features.notification.export.NotificationExport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import  jakarta.persistence.EntityManager;
import  jakarta.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class NotificationExportService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;
    private NotificationExport eirsListManagementExport;

    private AuditTrailService auditTrailService;

    public NotificationExportService(NotificationExport eirsListManagementExport, AuditTrailService auditTrailService) {
        this.eirsListManagementExport = eirsListManagementExport;
        this.auditTrailService = auditTrailService;
    }

    public MappingJacksonValue export(NotificationEntity notificationEntity) {
            String requestType = "NOTIFICATION_EXPORT";
            FileDetails export = eirsListManagementExport.export(notificationEntity, FeaturesEnum.getFeatureName(requestType).replace(" ", "_"));
            logger.info("requestType [" + requestType + "]");
            auditTrailService.auditTrailOperation(notificationEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
            return new MappingJacksonValue(export);
     }
}
