package app.apiservice.features.listmanagement.service;

import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.common.model.ResponseModel;
import app.apiservice.entity.app.EIRSListManagementEntity;
import app.apiservice.features.listmanagement.upload.EIRSListManagementFileUploadPayload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

@Service
public class FileUploadService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;

    private AuditTrailService auditTrailService;
    private EIRSListManagementFileUploadPayload eirsListManagementFileUploadPayload;
    private FeatureNameMap featureNameMap;

    public FileUploadService(AuditTrailService auditTrailService, EIRSListManagementFileUploadPayload eirsListManagementFileUploadPayload, FeatureNameMap featureNameMap) {
        this.auditTrailService = auditTrailService;
        this.eirsListManagementFileUploadPayload = eirsListManagementFileUploadPayload;
        this.featureNameMap = featureNameMap;
    }

    public ResponseModel save(EIRSListManagementEntity eirsListManagementEntity) {
        eirsListManagementEntity.setStatus("Init");
        Optional<String> optional = Optional.ofNullable(eirsListManagementEntity.getRequestType());
        if (optional.isPresent()) {
            ResponseModel upload = eirsListManagementFileUploadPayload.upload(eirsListManagementEntity);
            eirsListManagementFileUploadPayload.saveWebActionDBEntity(eirsListManagementEntity);
            String requestType = eirsListManagementEntity.getRequestType().isBlank() ? null : eirsListManagementEntity.getRequestType().toUpperCase();
            logger.info("requestType [" + requestType + "]");
            auditTrailService.auditTrailOperation(eirsListManagementEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("UPLOAD"));
            return upload;
        }
        return new ResponseModel(HttpStatus.EXPECTATION_FAILED.value(), "Pass Request type parameter in payload");
    }
}
