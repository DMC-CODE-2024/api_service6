package com.glocks.application.features.listmanagement.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.common.model.FileDetails;
import com.glocks.application.entity.app.BlockListEntity;
import com.glocks.application.entity.app.GrayListEntity;
import com.glocks.application.features.listmanagement.export.BlockListExport;
import com.glocks.application.features.listmanagement.export.GrayListExport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    public MappingJacksonValue export(GrayListEntity grayListEntity) {
        String requestType = "GRAY_LIST_DATA_EXPORT";
        FileDetails export = grayListExport.export(grayListEntity, FeaturesEnum.getFeatureName(requestType).replace(" ", "_"));
        logger.info("requestType ----------->[" + requestType + "]");
        auditTrailService.auditTrailOperation(grayListEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
        return new MappingJacksonValue(export);
    }
}
