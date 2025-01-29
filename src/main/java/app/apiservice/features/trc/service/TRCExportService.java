package app.apiservice.features.trc.service;

import app.apiservice.common.enums.FeaturesEnum;
import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.common.model.FileDetails;
import app.apiservice.common.model.ResponseModel;
import app.apiservice.entity.app.TRCDataManagementEntity;
import app.apiservice.features.trc.export.TypeApprovedExport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

@Service
public class TRCExportService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;
    private TypeApprovedExport typeApprovedExport;

    private AuditTrailService auditTrailService;

    public TRCExportService(TypeApprovedExport typeApprovedExport, AuditTrailService auditTrailService) {
        this.typeApprovedExport = typeApprovedExport;
        this.auditTrailService = auditTrailService;
    }

    @Autowired
    private FeatureNameMap featureNameMap;

    public MappingJacksonValue export(TRCDataManagementEntity trcDataManagementEntity) {
        Optional<String> optional = Optional.ofNullable(trcDataManagementEntity.getRequestType());
        if (optional.isPresent()) {
            String requestType = optional.get();
            FileDetails export = typeApprovedExport.export(trcDataManagementEntity, featureNameMap.get(requestType).replace(" ", "_"));
            // requestType = requestType.concat("_EXPORT");
            logger.info("requestType [" + requestType + "]");
            auditTrailService.auditTrailOperation(trcDataManagementEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("EXPORT"));
            return new MappingJacksonValue(export);
        }
        return new MappingJacksonValue(new ResponseModel(HttpStatus.EXPECTATION_FAILED.value(), "Pass Request type parameter in payload"));
    }
}
