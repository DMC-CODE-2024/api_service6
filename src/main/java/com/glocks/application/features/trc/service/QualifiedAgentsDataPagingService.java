package com.glocks.application.features.trc.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.entity.app.TRCDataManagementEntity;
import com.glocks.application.entity.app.TRCQualifiedAgentsDataEntity;
import com.glocks.application.features.trc.paging.QualifiedAgentsDataPaging;
import com.glocks.application.repository.app.QualifiedAgentsDataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

@Service
public class QualifiedAgentsDataPagingService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final QualifiedAgentsDataPaging qualifiedAgentsDataPaging;
    private final AuditTrailService auditTrailService;

    public QualifiedAgentsDataPagingService(QualifiedAgentsDataPaging qualifiedAgentsDataPaging, AuditTrailService auditTrailService) {
        this.qualifiedAgentsDataPaging = qualifiedAgentsDataPaging;
        this.auditTrailService = auditTrailService;
    }

    public MappingJacksonValue paging(TRCQualifiedAgentsDataEntity trcQualifiedAgentsDataEntity, int pageNo, int pageSize) {

        Page<TRCQualifiedAgentsDataEntity> page = null;
        page = qualifiedAgentsDataPaging.findAll(trcQualifiedAgentsDataEntity, pageNo, pageSize);
        String requestType = "QA_DATA_VIEWALL";
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(trcQualifiedAgentsDataEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
        return new MappingJacksonValue(page);

    }
}
