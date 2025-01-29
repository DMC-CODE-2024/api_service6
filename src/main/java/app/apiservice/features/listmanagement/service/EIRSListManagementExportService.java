package app.apiservice.features.listmanagement.service;

import app.apiservice.common.enums.FeaturesEnum;
import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.common.model.FileDetails;
import app.apiservice.common.model.ResponseModel;
import app.apiservice.entity.app.EIRSListManagementEntity;
import app.apiservice.features.listmanagement.export.EIRSListManagementExport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EIRSListManagementExportService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;
    private EIRSListManagementExport eirsListManagementExport;

    private AuditTrailService auditTrailService;

    public EIRSListManagementExportService(EIRSListManagementExport eirsListManagementExport, AuditTrailService auditTrailService) {
        this.eirsListManagementExport = eirsListManagementExport;
        this.auditTrailService = auditTrailService;
    }

    @Autowired
    private FeatureNameMap featureNameMap;

    public MappingJacksonValue export(EIRSListManagementEntity eirsListManagementEntity) {
        Optional<String> optional = Optional.ofNullable(eirsListManagementEntity.getRequestType());
        if (optional.isPresent()) {
            String requestType = optional.get();
            FileDetails export = eirsListManagementExport.export(eirsListManagementEntity, featureNameMap.get(requestType).replace(" ", "_"));
            logger.info("requestType [" + requestType + "]");
            auditTrailService.auditTrailOperation(eirsListManagementEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("EXPORT"));
            return new MappingJacksonValue(export);
        }
        return new MappingJacksonValue(new ResponseModel(HttpStatus.EXPECTATION_FAILED.value(), "Pass Request type parameter in payload"));
    }
}
