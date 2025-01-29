package app.apiservice.features.listmanagement.service;

import app.apiservice.common.enums.FeaturesEnum;
import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.entity.app.ExceptionListEntity;
import app.apiservice.features.listmanagement.paging.ExceptionListPaging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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

    @Autowired
    private FeatureNameMap featureNameMap;

    public MappingJacksonValue paging(ExceptionListEntity exceptionListEntity, int pageNo, int pageSize) {
        Page<ExceptionListEntity> page = exceptionListPaging.findAll(exceptionListEntity, pageNo, pageSize);
        String requestType = "VIEW_EXCEPTION_LIST";
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(exceptionListEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("VIEWALL"));
        return new MappingJacksonValue(page);
    }
}
