package app.apiservice.features.listmanagement.service;

import app.apiservice.common.enums.FeaturesEnum;
import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.entity.app.BlockListEntity;
import app.apiservice.features.listmanagement.paging.BlockListPaging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private FeatureNameMap featureNameMap;

    public MappingJacksonValue paging(BlockListEntity blockListEntity, int pageNo, int pageSize) {
        Page<BlockListEntity> page = blockListPaging.findAll(blockListEntity, pageNo, pageSize);
        String requestType = "VIEW_BLOCK_LIST";
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(blockListEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("VIEWALL"));
        return new MappingJacksonValue(page);
    }
}
