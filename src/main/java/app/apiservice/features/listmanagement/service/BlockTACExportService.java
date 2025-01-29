package app.apiservice.features.listmanagement.service;

import app.apiservice.common.enums.FeaturesEnum;
import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.common.model.FileDetails;
import app.apiservice.entity.app.BlockTACListEntity;
import app.apiservice.features.listmanagement.export.BlockTACExport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class BlockTACExportService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;
    private BlockTACExport blockTACExport;

    private AuditTrailService auditTrailService;

    public BlockTACExportService(BlockTACExport blockTACExport, AuditTrailService auditTrailService) {
        this.blockTACExport = blockTACExport;
        this.auditTrailService = auditTrailService;
    }

    @Autowired
    private FeatureNameMap featureNameMap;

    public MappingJacksonValue export(BlockTACListEntity blockTACListEntity) {
        String requestType = "VIEW_BLOCK_TAC";
        FileDetails export = blockTACExport.export(blockTACListEntity, featureNameMap.get(requestType).replace(" ", "_"));
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(blockTACListEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("EXPORT"));
        return new MappingJacksonValue(export);
    }
}
