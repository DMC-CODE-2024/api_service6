package com.glocks.application.features.listmanagement.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.common.model.FileDetails;
import com.glocks.application.common.model.ResponseModel;
import com.glocks.application.entity.app.ExceptionListEntity;
import com.glocks.application.entity.app.TRCDataManagementEntity;
import com.glocks.application.features.listmanagement.export.ExceptionListExport;
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

    public MappingJacksonValue export(ExceptionListEntity exceptionListEntity) {
        String requestType = "EXCEPTION_LIST_DATA_EXPORT";
        FileDetails export = exceptionListExport.export(exceptionListEntity, FeaturesEnum.getFeatureName(requestType).replace(" ", "_"));
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(exceptionListEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
        return new MappingJacksonValue(export);
    }
}
