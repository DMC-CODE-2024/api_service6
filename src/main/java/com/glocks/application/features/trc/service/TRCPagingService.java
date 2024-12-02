package com.glocks.application.features.trc.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.entity.app.TRCDataManagementEntity;
import com.glocks.application.features.trc.paging.TypeApprovedPaging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import  jakarta.persistence.EntityManager;
import  jakarta.persistence.PersistenceContext;
import java.util.Objects;
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

    public MappingJacksonValue paging(TRCDataManagementEntity trcDataManagementEntity, int pageNo, int pageSize) {
        Page<TRCDataManagementEntity> page = typeApprovedPaging.findAll(trcDataManagementEntity, pageNo, pageSize);
        String requestType = trcDataManagementEntity.getRequestType().concat("_VIEWALL");
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(trcDataManagementEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
        return new MappingJacksonValue(page);
    }

    public TRCDataManagementEntity find(TRCDataManagementEntity trcDataManagementEntity) {
        Optional<TRCDataManagementEntity> result = typeApprovedPaging.find(trcDataManagementEntity.getId());
        logger.info("Record found in DB {}", result);
        if (result.isPresent()) {
            String requestType = trcDataManagementEntity.getRequestType().concat("_VIEW");
            logger.info("requestType [" + requestType + "]");
            auditTrailService.auditTrailOperation(trcDataManagementEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
            return result.get();
        }
        logger.info("No record found for ID {}", trcDataManagementEntity.getId());
        return null;
    }
}
