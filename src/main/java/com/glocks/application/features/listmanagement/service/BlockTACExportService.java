package com.glocks.application.features.listmanagement.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.common.model.FileDetails;
import com.glocks.application.entity.app.BlockTACListEntity;
import com.glocks.application.features.listmanagement.export.BlockTACExport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import  jakarta.persistence.EntityManager;
import  jakarta.persistence.PersistenceContext;

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

    public MappingJacksonValue export(BlockTACListEntity blockTACListEntity) {
        String requestType = "BLOCK_TAC_DATA_EXPORT";
        FileDetails export = blockTACExport.export(blockTACListEntity, FeaturesEnum.getFeatureName(requestType).replace(" ", "_"));
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(blockTACListEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
        return new MappingJacksonValue(export);
    }
}
