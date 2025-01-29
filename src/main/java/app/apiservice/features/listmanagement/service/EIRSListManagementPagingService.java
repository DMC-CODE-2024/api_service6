package app.apiservice.features.listmanagement.service;

import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.entity.app.EIRSListManagementEntity;
import app.apiservice.features.listmanagement.paging.EIRSListManagementPaging;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private FeatureNameMap featureNameMap;

    public MappingJacksonValue paging(EIRSListManagementEntity eirsListManagementEntity, int pageNo, int pageSize) {
        Page<EIRSListManagementEntity> page = exceptionListPaging.findAll(eirsListManagementEntity, pageNo, pageSize);
        String requestType = eirsListManagementEntity.getRequestType().isBlank() ? null : eirsListManagementEntity.getRequestType().toUpperCase();
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(eirsListManagementEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("VIEWALL"));
        return new MappingJacksonValue(page);
    }

    public MappingJacksonValue find(EIRSListManagementEntity eirsListManagementEntity) {
        Optional<EIRSListManagementEntity> optional = exceptionListPaging.find(eirsListManagementEntity.getId());
        String requestType = optional.get().getRequestType().isBlank() ? null: optional.get().getRequestType().toUpperCase();

        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(eirsListManagementEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("VIEW"));
        return new MappingJacksonValue(optional.get());
    }
}
