package app.apiservice.features.listmanagement.service;

import app.apiservice.common.enums.FeaturesEnum;
import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.entity.app.GrayListEntity;
import app.apiservice.features.listmanagement.paging.GrayListPaging;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private FeatureNameMap featureNameMap;

    public MappingJacksonValue paging(GrayListEntity grayListEntity, int pageNo, int pageSize) {
        Page<GrayListEntity> page = grayListPaging.findAll(grayListEntity, pageNo, pageSize);
        String requestType = "GRAY_LIST";
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(grayListEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("VIEWALL"));
        return new MappingJacksonValue(page);
    }
}
