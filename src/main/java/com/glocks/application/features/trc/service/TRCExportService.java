package com.glocks.application.features.trc.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.common.model.FileDetails;
import com.glocks.application.common.model.ResponseModel;
import com.glocks.application.entity.app.TRCDataManagementEntity;
import com.glocks.application.features.trc.export.TypeApprovedExport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import  jakarta.persistence.EntityManager;
import  jakarta.persistence.PersistenceContext;
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

    public MappingJacksonValue export(TRCDataManagementEntity trcDataManagementEntity) {
        Optional<String> optional = Optional.ofNullable(trcDataManagementEntity.getRequestType());
        if (optional.isPresent()) {
            String requestType = optional.get();
            FileDetails export = typeApprovedExport.export(trcDataManagementEntity, FeaturesEnum.getSubFeatureName(requestType).replace(" ", "_"));
            requestType = requestType.concat("_EXPORT");
            logger.info("requestType [" + requestType + "]");
            auditTrailService.auditTrailOperation(trcDataManagementEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
            return new MappingJacksonValue(export);
        }
        return new MappingJacksonValue(new ResponseModel(HttpStatus.EXPECTATION_FAILED.value(), "Pass Request type parameter in payload"));
    }
}
