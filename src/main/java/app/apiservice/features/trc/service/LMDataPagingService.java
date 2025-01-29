package app.apiservice.features.trc.service;

import app.apiservice.common.enums.FeaturesEnum;
import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.entity.app.TRCLocalManufacturedDevicesDumpEntity;
import app.apiservice.features.trc.export.LMDataExport;
import app.apiservice.features.trc.interfaces.ExportFeatureI;
import app.apiservice.features.trc.paging.LMDataPaging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private FeatureNameMap featureNameMap;
    String requestType = "VIEW_LM";

    public MappingJacksonValue paging(TRCLocalManufacturedDevicesDumpEntity trcLocalManufacturedDevicesDumpEntity, int pageNo, int pageSize) {
        Page<TRCLocalManufacturedDevicesDumpEntity> page = null;
        page = lmDataPaging.findAll(trcLocalManufacturedDevicesDumpEntity, pageNo, pageSize);
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(trcLocalManufacturedDevicesDumpEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("VIEWALL"));
        return new MappingJacksonValue(page);
    }

    public MappingJacksonValue export(TRCLocalManufacturedDevicesDumpEntity trcLocalManufacturedDevicesDumpEntity) {
        ExportFeatureI<TRCLocalManufacturedDevicesDumpEntity> exportFeatureI = (x) ->
                lmDataExport.export(x, featureNameMap.get(requestType));
        auditTrailService.auditTrailOperation(trcLocalManufacturedDevicesDumpEntity.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("EXPORT"));
        return new MappingJacksonValue(exportFeatureI.export(trcLocalManufacturedDevicesDumpEntity));
    }
}
