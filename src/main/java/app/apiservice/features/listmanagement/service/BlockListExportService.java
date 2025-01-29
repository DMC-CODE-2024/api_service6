package app.apiservice.features.listmanagement.service;

import app.apiservice.common.enums.FeaturesEnum;
import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.common.model.FileDetails;
import app.apiservice.entity.app.BlockListEntity;
import app.apiservice.features.listmanagement.export.BlockListExport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import  jakarta.persistence.EntityManager;
import  jakarta.persistence.PersistenceContext;

@Service
public class BlockListExportService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;
    private BlockListExport blockListExport;

    private AuditTrailService auditTrailService;

    public BlockListExportService(BlockListExport blockListExport, AuditTrailService auditTrailService) {
        this.blockListExport = blockListExport;
        this.auditTrailService = auditTrailService;
    }
    @Autowired
    private FeatureNameMap featureNameMap;

    public MappingJacksonValue export(BlockListEntity blockListEntity) {
        String requestType = "VIEW_BLOCK_LIST";
        FileDetails export = blockListExport.export(blockListEntity, featureNameMap.get(requestType).replace(" ", "_"));
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(blockListEntity.getAuditTrailModel(),  featureNameMap.get(requestType), featureNameMap.get("EXPORT"));
        return new MappingJacksonValue(export);
    }
}
