package com.glocks.application.features.listmanagement.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.entity.app.BlockListEntity;
import com.glocks.application.entity.app.ExceptionListEntity;
import com.glocks.application.features.listmanagement.paging.BlockListPaging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import  jakarta.persistence.EntityManager;
import  jakarta.persistence.PersistenceContext;

@Service
public class BlockListPagingService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;
    private BlockListPaging blockListPaging;

    private AuditTrailService auditTrailService;

    public BlockListPagingService(BlockListPaging blockListPaging, AuditTrailService auditTrailService) {
        this.blockListPaging = blockListPaging;
        this.auditTrailService = auditTrailService;
    }

    public MappingJacksonValue paging(BlockListEntity blockListEntity, int pageNo, int pageSize) {
        Page<BlockListEntity> page = blockListPaging.findAll(blockListEntity, pageNo, pageSize);
        String requestType = "BLOCK_LIST_DATA_VIEWALL";
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(blockListEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
        return new MappingJacksonValue(page);
    }
}
