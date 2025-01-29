package app.apiservice.features.notification.service;

import app.apiservice.common.enums.FeaturesEnum;
import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.common.model.FileDetails;
import app.apiservice.entity.app.NotificationEntity;
import app.apiservice.features.notification.export.NotificationExport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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

    @Autowired
    private FeatureNameMap featureNameMap;

    public MappingJacksonValue export(NotificationEntity notificationEntity) {
        String requestType = featureNameMap.get("NOTIFICATION");
        FileDetails export = eirsListManagementExport.export(notificationEntity, requestType.replace(" ", "_"));
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(notificationEntity.getAuditTrailModel(), requestType, featureNameMap.get("EXPORT"));
        return new MappingJacksonValue(export);
    }
}
