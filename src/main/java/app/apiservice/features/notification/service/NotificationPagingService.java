package app.apiservice.features.notification.service;

import app.apiservice.common.enums.FeaturesEnum;
import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.entity.app.NotificationEntity;
import app.apiservice.features.notification.paging.NotificationPaging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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

    @Autowired
    private FeatureNameMap featureNameMap;
    String requestType = "NOTIFICATION";

    public MappingJacksonValue paging(NotificationEntity notificationEntity, int pageNo, int pageSize) {
        Page<NotificationEntity> page = notificationPaging.findAll(notificationEntity, pageNo, pageSize);

        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(notificationEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("VIEWALL"));
        return new MappingJacksonValue(page);
    }

    public MappingJacksonValue findById(NotificationEntity notificationEntity) {
        NotificationEntity result = notificationPaging.findById(notificationEntity.getId());
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(notificationEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("VIEW"));
        return new MappingJacksonValue(result);
    }
}
