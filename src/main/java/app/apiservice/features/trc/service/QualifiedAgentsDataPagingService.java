package app.apiservice.features.trc.service;

import app.apiservice.common.enums.FeaturesEnum;
import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.entity.app.TRCQualifiedAgentsDataEntity;
import app.apiservice.features.trc.paging.QualifiedAgentsDataPaging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private FeatureNameMap featureNameMap;
    String requestType = "VIEW_QA";
    public MappingJacksonValue paging(TRCQualifiedAgentsDataEntity trcQualifiedAgentsDataEntity, int pageNo, int pageSize) {

        Page<TRCQualifiedAgentsDataEntity> page = null;
        page = qualifiedAgentsDataPaging.findAll(trcQualifiedAgentsDataEntity, pageNo, pageSize);
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(trcQualifiedAgentsDataEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("VIEWALL"));
        return new MappingJacksonValue(page);

    }
}
