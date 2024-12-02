package com.glocks.application.features.listmanagement.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.common.model.ResponseModel;
import com.glocks.application.entity.app.EIRSListManagementEntity;
import com.glocks.application.features.listmanagement.upload.EIRSListManagementFileUploadPayload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import  jakarta.persistence.EntityManager;
import  jakarta.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class FileUploadService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;

    private AuditTrailService auditTrailService;
    private EIRSListManagementFileUploadPayload eirsListManagementFileUploadPayload;

    public FileUploadService(AuditTrailService auditTrailService, EIRSListManagementFileUploadPayload eirsListManagementFileUploadPayload) {
        this.auditTrailService = auditTrailService;
        this.eirsListManagementFileUploadPayload = eirsListManagementFileUploadPayload;
    }

    public ResponseModel save(EIRSListManagementEntity eirsListManagementEntity) {
        eirsListManagementEntity.setStatus("Init");
        Optional<String> optional = Optional.ofNullable(eirsListManagementEntity.getRequestType());
        if (optional.isPresent()) {
            ResponseModel upload = eirsListManagementFileUploadPayload.upload(eirsListManagementEntity);
            eirsListManagementFileUploadPayload.saveWebActionDBEntity(eirsListManagementEntity);
            String requestType = eirsListManagementEntity.getRequestType().toUpperCase().concat("_UPLOAD");
            logger.info("requestType [" + requestType + "]");
            auditTrailService.auditTrailOperation(eirsListManagementEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
            return upload;
        }
        return new ResponseModel(HttpStatus.EXPECTATION_FAILED.value(), "Pass Request type parameter in payload");
    }
}
