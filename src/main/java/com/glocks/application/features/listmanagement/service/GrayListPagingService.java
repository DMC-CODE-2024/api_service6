package com.glocks.application.features.listmanagement.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.entity.app.BlockListEntity;
import com.glocks.application.entity.app.GrayListEntity;
import com.glocks.application.features.listmanagement.paging.BlockListPaging;
import com.glocks.application.features.listmanagement.paging.GrayListPaging;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;


@Service
public class GrayListPagingService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;
    private GrayListPaging grayListPaging;

    private AuditTrailService auditTrailService;

    public GrayListPagingService(GrayListPaging grayListPaging, AuditTrailService auditTrailService) {
        this.grayListPaging = grayListPaging;
        this.auditTrailService = auditTrailService;
    }

    public MappingJacksonValue paging(GrayListEntity grayListEntity, int pageNo, int pageSize) {
        Page<GrayListEntity> page = grayListPaging.findAll(grayListEntity, pageNo, pageSize);
        String requestType = "GRAY_LIST_DATA_VIEWALL";
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(grayListEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
        return new MappingJacksonValue(page);
    }
}
