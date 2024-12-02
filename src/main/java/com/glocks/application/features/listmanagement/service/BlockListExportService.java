package com.glocks.application.features.listmanagement.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.common.model.FileDetails;
import com.glocks.application.entity.app.BlockListEntity;
import com.glocks.application.features.listmanagement.export.BlockListExport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    public MappingJacksonValue export(BlockListEntity blockListEntity) {
        String requestType = "BLOCK_LIST_DATA_EXPORT";
        FileDetails export = blockListExport.export(blockListEntity, FeaturesEnum.getFeatureName(requestType).replace(" ", "_"));
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(blockListEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
        return new MappingJacksonValue(export);
    }
}
