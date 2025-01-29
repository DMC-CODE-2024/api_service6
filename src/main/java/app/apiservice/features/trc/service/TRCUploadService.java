package app.apiservice.features.trc.service;

import app.apiservice.common.enums.FeaturesEnum;
import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.common.model.ResponseModel;
import app.apiservice.entity.app.TRCDataManagementEntity;
import app.apiservice.features.trc.upload.TRCFileUploadPayload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

@Service
public class TRCUploadService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;

    private final AuditTrailService auditTrailService;
    private TRCFileUploadPayload fileUploadPayload;

    public TRCUploadService(AuditTrailService auditTrailService, TRCFileUploadPayload fileUploadPayload) {
        this.auditTrailService = auditTrailService;
        this.fileUploadPayload = fileUploadPayload;
    }

    @Autowired
    private FeatureNameMap featureNameMap;

    public ResponseModel upload(TRCDataManagementEntity trcDataManagementEntity) {
        trcDataManagementEntity.setStatus("INIT");
        Optional<String> optional = Optional.ofNullable(trcDataManagementEntity.getRequestType());
        if (optional.isPresent()) {
            ResponseModel upload = fileUploadPayload.upload(trcDataManagementEntity);
            fileUploadPayload.saveWebActionDBEntity(trcDataManagementEntity);
            String requestType = trcDataManagementEntity.getRequestType().isBlank() ? "NULL" : trcDataManagementEntity.getRequestType().toUpperCase();
            logger.info("requestType [" + requestType + "]");
            auditTrailService.auditTrailOperation(trcDataManagementEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("UPLOAD"));
            return upload;
        }
        return new ResponseModel(HttpStatus.EXPECTATION_FAILED.value(), "Pass Request type parameter in payload");
    }
}
