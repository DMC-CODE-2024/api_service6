package com.glocks.application.features.listmanagement.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.entity.app.BlockListEntity;
import com.glocks.application.entity.app.BlockTACListEntity;
import com.glocks.application.features.listmanagement.paging.BlockListPaging;
import com.glocks.application.features.listmanagement.paging.BlockTACPaging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import  jakarta.persistence.EntityManager;
import  jakarta.persistence.PersistenceContext;

@Service
public class BlockTACPagingService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;
    private BlockTACPaging blockTACPaging;

    private AuditTrailService auditTrailService;

    public BlockTACPagingService(BlockTACPaging blockTACPaging, AuditTrailService auditTrailService) {
        this.blockTACPaging = blockTACPaging;
        this.auditTrailService = auditTrailService;
    }

    public MappingJacksonValue paging(BlockTACListEntity blockTACListEntity, int pageNo, int pageSize) {
        Page<BlockTACListEntity> page = blockTACPaging.findAll(blockTACListEntity, pageNo, pageSize);
        String requestType = "BLOCK_TAC_DATA_VIEWALL";
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(blockTACListEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
        return new MappingJacksonValue(page);
    }
}
