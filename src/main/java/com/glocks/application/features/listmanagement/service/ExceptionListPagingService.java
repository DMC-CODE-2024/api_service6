package com.glocks.application.features.listmanagement.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.entity.app.EIRSListManagementEntity;
import com.glocks.application.entity.app.ExceptionListEntity;
import com.glocks.application.features.listmanagement.paging.EIRSListManagementPaging;
import com.glocks.application.features.listmanagement.paging.ExceptionListPaging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import  jakarta.persistence.EntityManager;
import  jakarta.persistence.PersistenceContext;

@Service
public class ExceptionListPagingService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;
    private ExceptionListPaging exceptionListPaging;

    private AuditTrailService auditTrailService;

    public ExceptionListPagingService(ExceptionListPaging exceptionListPaging, AuditTrailService auditTrailService) {
        this.exceptionListPaging = exceptionListPaging;
        this.auditTrailService = auditTrailService;
    }

    public MappingJacksonValue paging(ExceptionListEntity exceptionListEntity, int pageNo, int pageSize) {
        Page<ExceptionListEntity> page = exceptionListPaging.findAll(exceptionListEntity, pageNo, pageSize);
        String requestType = "EXCEPTION_LIST_DATA_VIEWALL";
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(exceptionListEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
        return new MappingJacksonValue(page);
    }
}
