package com.glocks.application.features.trc.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.common.model.FileDetails;
import com.glocks.application.common.model.ResponseModel;
import com.glocks.application.entity.app.TRCDataManagementEntity;
import com.glocks.application.entity.app.TRCQualifiedAgentsDataEntity;
import com.glocks.application.features.trc.export.QualifiedAgentsDataExport;
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
public class QualifiedAgentsDataExportService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;
    private final QualifiedAgentsDataExport qualifiedAgentsDataExport;

    private final AuditTrailService auditTrailService;

    public QualifiedAgentsDataExportService(QualifiedAgentsDataExport qualifiedAgentsDataExport, AuditTrailService auditTrailService) {
        this.qualifiedAgentsDataExport = qualifiedAgentsDataExport;
        this.auditTrailService = auditTrailService;
    }

    public MappingJacksonValue export(TRCQualifiedAgentsDataEntity trcQualifiedAgentsDataEntity) {
        String requestType = "QA_DATA_EXPORT";
        FileDetails export = qualifiedAgentsDataExport.export(trcQualifiedAgentsDataEntity,FeaturesEnum.getFeatureName(requestType).replace(" ", "_"));
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(trcQualifiedAgentsDataEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
        return new MappingJacksonValue(export);
    }
}
