package com.glocks.application.features.listmanagement.upload;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.enums.WebActionDbState;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.common.model.ResponseModel;
import com.glocks.application.entity.app.EIRSListManagementEntity;
import com.glocks.application.entity.app.WebActionDBEntity;
import com.glocks.application.repository.app.EIRSListManagementRepository;
import com.glocks.application.repository.app.WebActionDBRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

@Service
public class EIRSListManagementFileUploadPayload<T> {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private EIRSListManagementRepository eirsListManagementRepository;
    private WebActionDBRepository webActionDBRepository;
    private AuditTrailService auditTrailService;

    public EIRSListManagementFileUploadPayload(EIRSListManagementRepository eirsListManagementRepository, WebActionDBRepository webActionDBRepository, AuditTrailService auditTrailService) {
        this.eirsListManagementRepository = eirsListManagementRepository;
        this.webActionDBRepository = webActionDBRepository;
        this.auditTrailService = auditTrailService;
    }

    @Transactional(rollbackOn = {SQLException.class})
    public <T extends EIRSListManagementEntity> ResponseModel upload(T t) {
        ResponseModel responseModel = new ResponseModel();
        if (Objects.nonNull(t)) {
            logger.info("EIRSListManagementEntity payload[" + t + "]");
            eirsListManagementRepository.save(t);
            responseModel.setData("Successfully Saved!").setStatusCode(HttpStatus.OK.value());
        }
        return responseModel;
    }

    public void saveWebActionDBEntity(EIRSListManagementEntity eirsListManagementEntity) {
        Optional<String> optional = Optional.ofNullable(eirsListManagementEntity.getTransactionId());
        if (optional.isPresent()) {
            String txnID = optional.get();
            logger.info("txnID [" + txnID + "]");
            String requestType = eirsListManagementEntity.getRequestType().concat("_WP");
            logger.info("requestType [" + requestType + "]");
            WebActionDBEntity webActionDBEntity = new WebActionDBEntity().setFeature(FeaturesEnum.getFeatureName(requestType)).setSubFeature(FeaturesEnum.getSubFeatureName(requestType)).setState(WebActionDbState.PROCESSING.getCode()).setTxnId(txnID);
            logger.info("WebActionDBEntity payload[" + webActionDBEntity + "]");
            webActionDBRepository.save(webActionDBEntity);
        }
    }
}
