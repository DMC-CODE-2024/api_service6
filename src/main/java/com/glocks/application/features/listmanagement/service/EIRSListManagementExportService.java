package com.glocks.application.features.listmanagement.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.common.model.FileDetails;
import com.glocks.application.common.model.ResponseModel;
import com.glocks.application.entity.app.EIRSListManagementEntity;
import com.glocks.application.features.listmanagement.export.EIRSListManagementExport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    public MappingJacksonValue export(EIRSListManagementEntity eirsListManagementEntity) {
        Optional<String> optional = Optional.ofNullable(eirsListManagementEntity.getRequestType());
        if (optional.isPresent()) {
            String requestType = optional.get();
            FileDetails export = eirsListManagementExport.export(eirsListManagementEntity, FeaturesEnum.getSubFeatureName(requestType).replace(" ", "_"));
            logger.info("requestType [" + requestType + "]");
            auditTrailService.auditTrailOperation(eirsListManagementEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType.concat("_EXPORT")), FeaturesEnum.getSubFeatureName(requestType.concat("_EXPORT")));
            return new MappingJacksonValue(export);
        }
        return new MappingJacksonValue(new ResponseModel(HttpStatus.EXPECTATION_FAILED.value(), "Pass Request type parameter in payload"));
    }
}
