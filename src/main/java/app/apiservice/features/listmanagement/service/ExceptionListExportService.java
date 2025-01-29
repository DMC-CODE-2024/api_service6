package app.apiservice.features.listmanagement.service;

import app.apiservice.common.enums.FeaturesEnum;
import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.common.model.FileDetails;
import app.apiservice.entity.app.ExceptionListEntity;
import app.apiservice.features.listmanagement.export.ExceptionListExport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import  jakarta.persistence.EntityManager;
import  jakarta.persistence.PersistenceContext;

@Service
public class ExceptionListExportService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;
    private ExceptionListExport exceptionListExport;

    private AuditTrailService auditTrailService;

    public ExceptionListExportService(ExceptionListExport exceptionListExport, AuditTrailService auditTrailService) {
        this.exceptionListExport = exceptionListExport;
        this.auditTrailService = auditTrailService;
    }

    @Autowired
    private FeatureNameMap featureNameMap;

    public MappingJacksonValue export(ExceptionListEntity exceptionListEntity) {
        String requestType = "VIEW_EXCEPTION_LIST";
        FileDetails export = exceptionListExport.export(exceptionListEntity, featureNameMap.get(requestType).replace(" ", "_"));
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(exceptionListEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("EXPORT"));
        return new MappingJacksonValue(export);
    }
}
