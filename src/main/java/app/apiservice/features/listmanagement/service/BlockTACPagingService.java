package app.apiservice.features.listmanagement.service;

import app.apiservice.common.enums.FeaturesEnum;
import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.entity.app.BlockTACListEntity;
import app.apiservice.features.listmanagement.paging.BlockTACPaging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private FeatureNameMap featureNameMap;

    public MappingJacksonValue paging(BlockTACListEntity blockTACListEntity, int pageNo, int pageSize) {
        Page<BlockTACListEntity> page = blockTACPaging.findAll(blockTACListEntity, pageNo, pageSize);
        String requestType = "VIEW_BLOCK_TAC";
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(blockTACListEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("VIEWALL"));
        return new MappingJacksonValue(page);
    }
}
