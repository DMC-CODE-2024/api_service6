package app.apiservice.features.trc.service;

import app.apiservice.common.enums.FeaturesEnum;
import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.entity.app.TRCDataManagementEntity;
import app.apiservice.features.trc.paging.TypeApprovedPaging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

@Service
public class TRCPagingService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;
    private final TypeApprovedPaging typeApprovedPaging;

    private final AuditTrailService auditTrailService;

    public TRCPagingService(TypeApprovedPaging typeApprovedPaging, AuditTrailService auditTrailService) {
        this.typeApprovedPaging = typeApprovedPaging;
        this.auditTrailService = auditTrailService;
    }

    @Autowired
    private FeatureNameMap featureNameMap;

    public MappingJacksonValue paging(TRCDataManagementEntity trcDataManagementEntity, int pageNo, int pageSize) {
        Page<TRCDataManagementEntity> page = typeApprovedPaging.findAll(trcDataManagementEntity, pageNo, pageSize);
        String requestType = trcDataManagementEntity.getRequestType().isBlank() ? null : trcDataManagementEntity.getRequestType().toUpperCase();

        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(trcDataManagementEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("VIEWALL"));
        return new MappingJacksonValue(page);
    }

    public TRCDataManagementEntity find(TRCDataManagementEntity trcDataManagementEntity) {
        Optional<TRCDataManagementEntity> result = typeApprovedPaging.find(trcDataManagementEntity.getId());
        logger.info("Record found in DB {}", result);
        if (result.isPresent()) {
            String requestType = trcDataManagementEntity.getRequestType().isBlank() ? null : trcDataManagementEntity.getRequestType().toUpperCase();
            logger.info("requestType [" + requestType + "]");
            auditTrailService.auditTrailOperation(trcDataManagementEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("VIEW"));
            return result.get();
        }
        logger.info("No record found for ID {}", trcDataManagementEntity.getId());
        return null;
    }
}
