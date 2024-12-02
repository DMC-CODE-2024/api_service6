package com.glocks.application.features.listmanagement.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.entity.app.EIRSListManagementEntity;
import com.glocks.application.features.listmanagement.paging.EIRSListManagementPaging;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EIRSListManagementPagingService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;
    private EIRSListManagementPaging exceptionListPaging;

    private AuditTrailService auditTrailService;

    public EIRSListManagementPagingService(EIRSListManagementPaging exceptionListPaging, AuditTrailService auditTrailService) {
        this.exceptionListPaging = exceptionListPaging;
        this.auditTrailService = auditTrailService;
    }

    public MappingJacksonValue paging(EIRSListManagementEntity eirsListManagementEntity, int pageNo, int pageSize) {
        Page<EIRSListManagementEntity> page = exceptionListPaging.findAll(eirsListManagementEntity, pageNo, pageSize);
        String requestType = eirsListManagementEntity.getRequestType().concat("_VIEWALL");
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(eirsListManagementEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
        return new MappingJacksonValue(page);
    }

    public MappingJacksonValue find(EIRSListManagementEntity eirsListManagementEntity) {
        Optional<EIRSListManagementEntity> optional = exceptionListPaging.find(eirsListManagementEntity.getId());
        String requestType = optional.get().getRequestType().concat("_VIEW");
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(eirsListManagementEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
        return new MappingJacksonValue(optional.get());
    }
}
