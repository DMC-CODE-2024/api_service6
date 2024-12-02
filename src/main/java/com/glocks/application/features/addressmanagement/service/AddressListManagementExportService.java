package com.glocks.application.features.addressmanagement.service;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.common.model.FileDetails;
import com.glocks.application.entity.app.AddressEntity;
import com.glocks.application.features.addressmanagement.export.AddressListManagementExport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;


@Service
public class AddressListManagementExportService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;
    private AddressListManagementExport addressListManagementExport;

    private AuditTrailService auditTrailService;

    public AddressListManagementExportService(AddressListManagementExport addressListManagementExport, AuditTrailService auditTrailService) {
        this.addressListManagementExport = addressListManagementExport;
        this.auditTrailService = auditTrailService;
    }

    public MappingJacksonValue export(AddressEntity addressEntity) {

        String requestType = "ADDRESS_MGMT_EXPORT";
        FileDetails export = addressListManagementExport.export(addressEntity, FeaturesEnum.getFeatureName(requestType).replace(" ", "_"));
        logger.info("requestType [" + requestType + "]");
        auditTrailService.auditTrailOperation(addressEntity.getAuditTrailModel(), FeaturesEnum.getFeatureName(requestType), FeaturesEnum.getSubFeatureName(requestType));
        return new MappingJacksonValue(export);
    }
}
