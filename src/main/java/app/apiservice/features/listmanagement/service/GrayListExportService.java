package app.apiservice.features.listmanagement.service;

import app.apiservice.common.enums.FeaturesEnum;
import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.common.model.FileDetails;
import app.apiservice.entity.app.GrayListEntity;
import app.apiservice.features.listmanagement.export.GrayListExport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;


@Service
public class GrayListExportService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;
    private GrayListExport grayListExport;

    private AuditTrailService auditTrailService;

    public GrayListExportService(GrayListExport grayListExport, AuditTrailService auditTrailService) {
        this.grayListExport = grayListExport;
        this.auditTrailService = auditTrailService;
    }

    @Autowired
    private FeatureNameMap featureNameMap;

    public MappingJacksonValue export(GrayListEntity grayListEntity) {
        String requestType = "GRAY_LIST";
        FileDetails export = grayListExport.export(grayListEntity, featureNameMap.get(requestType).replace(" ", "_"));
        logger.info("requestType ----------->[" + requestType + "]");
        auditTrailService.auditTrailOperation(grayListEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("EXPORT"));
        return new MappingJacksonValue(export);
    }
}
