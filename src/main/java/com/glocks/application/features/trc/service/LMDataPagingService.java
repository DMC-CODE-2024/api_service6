package com.glocks.application.features.trc.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.entity.app.TRCLocalManufacturedDevicesDumpEntity;
import com.glocks.application.features.trc.export.LMDataExport;
import com.glocks.application.features.trc.interfaces.ExportFeatureI;
import com.glocks.application.features.trc.paging.LMDataPaging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

@Service
public class LMDataPagingService {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private LMDataPaging lmDataPaging;
    private LMDataExport lmDataExport;
    private AuditTrailService auditTrailService;

    public LMDataPagingService(LMDataPaging lmDataPaging, AuditTrailService auditTrailService, LMDataExport lmDataExport) {
        this.lmDataPaging = lmDataPaging;
        this.auditTrailService = auditTrailService;
        this.lmDataExport = lmDataExport;
    }

    public MappingJacksonValue paging(TRCLocalManufacturedDevicesDumpEntity trcLocalManufacturedDevicesDumpEntity, int pageNo, int pageSize) {
        Page<TRCLocalManufacturedDevicesDumpEntity> page = null;
        page = lmDataPaging.findAll(trcLocalManufacturedDevicesDumpEntity, pageNo, pageSize);
        String requestType = "LM_DATA_VIEWALL";
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(trcLocalManufacturedDevicesDumpEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
        return new MappingJacksonValue(page);
    }

    public MappingJacksonValue export(TRCLocalManufacturedDevicesDumpEntity trcLocalManufacturedDevicesDumpEntity) {

        String requestType = "LM_DATA_EXPORT";
        ExportFeatureI<TRCLocalManufacturedDevicesDumpEntity> exportFeatureI = (x) ->
                lmDataExport.export(x, FeaturesEnum.getFeatureName(requestType));
        auditTrailService.auditTrailOperation(trcLocalManufacturedDevicesDumpEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
        return new MappingJacksonValue(exportFeatureI.export(trcLocalManufacturedDevicesDumpEntity));
    }
}
