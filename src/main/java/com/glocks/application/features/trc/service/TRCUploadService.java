package com.glocks.application.features.trc.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.common.model.ResponseModel;
import com.glocks.application.entity.app.TRCDataManagementEntity;
import com.glocks.application.features.trc.upload.TRCFileUploadPayload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import  jakarta.persistence.EntityManager;
import  jakarta.persistence.PersistenceContext;
import  jakarta.persistence.criteria.CriteriaBuilder;
import  jakarta.persistence.criteria.CriteriaQuery;
import  jakarta.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

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

    public ResponseModel upload(TRCDataManagementEntity trcDataManagementEntity) {
        trcDataManagementEntity.setStatus("INIT");
        Optional<String> optional = Optional.ofNullable(trcDataManagementEntity.getRequestType());
        if (optional.isPresent()) {
            ResponseModel upload = fileUploadPayload.upload(trcDataManagementEntity);
            fileUploadPayload.saveWebActionDBEntity(trcDataManagementEntity);
            String requestType = trcDataManagementEntity.getRequestType().toUpperCase().concat("_UPLOAD");
            logger.info("requestType [" + requestType + "]");
            auditTrailService.auditTrailOperation(trcDataManagementEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
            return upload;
        }
        return new ResponseModel(HttpStatus.EXPECTATION_FAILED.value(), "Pass Request type parameter in payload");
    }
}
