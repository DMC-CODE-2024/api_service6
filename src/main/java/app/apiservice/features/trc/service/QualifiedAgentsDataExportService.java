package app.apiservice.features.trc.service;

import app.apiservice.common.enums.FeaturesEnum;
import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.common.model.FileDetails;
import app.apiservice.entity.app.TRCQualifiedAgentsDataEntity;
import app.apiservice.features.trc.export.QualifiedAgentsDataExport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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

    @Autowired
    private FeatureNameMap featureNameMap;
    String requestType = "VIEW_QA";

    public MappingJacksonValue export(TRCQualifiedAgentsDataEntity trcQualifiedAgentsDataEntity) {
        FileDetails export = qualifiedAgentsDataExport.export(trcQualifiedAgentsDataEntity, featureNameMap.get(requestType).replace(" ", "_"));
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(trcQualifiedAgentsDataEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("EXPORT"));
        return new MappingJacksonValue(export);
    }
}
