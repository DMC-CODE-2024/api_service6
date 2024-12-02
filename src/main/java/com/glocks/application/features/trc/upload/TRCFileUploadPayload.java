package com.glocks.application.features.trc.upload;

import com.glocks.application.common.enums.FeaturesEnum;
import com.glocks.application.common.enums.WebActionDbState;
import com.glocks.application.common.features.audit_trail.AuditTrailService;
import com.glocks.application.common.model.ResponseModel;
import com.glocks.application.entity.app.TRCDataManagementEntity;
import com.glocks.application.entity.app.WebActionDBEntity;
import com.glocks.application.repository.app.TypeApprovedRepo;
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
public class TRCFileUploadPayload<T> {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private TypeApprovedRepo typeApprovedRepo;
    private WebActionDBRepository webActionDBRepository;
    private AuditTrailService auditTrailService;

    public TRCFileUploadPayload(TypeApprovedRepo typeApprovedRepo, WebActionDBRepository webActionDBRepository, AuditTrailService auditTrailService) {
        this.typeApprovedRepo = typeApprovedRepo;
        this.webActionDBRepository = webActionDBRepository;
        this.auditTrailService = auditTrailService;
    }

    @Transactional(rollbackOn = {SQLException.class})
    public <T extends TRCDataManagementEntity> ResponseModel upload(T trcDataManagementEntity) {
        ResponseModel responseModel = new ResponseModel();
        if (Objects.nonNull(trcDataManagementEntity)) {
            logger.info("TRCDataManagementEntity payload[" + trcDataManagementEntity + "]");
            typeApprovedRepo.save(trcDataManagementEntity);
            responseModel.setData("Successfully Saved!").setStatusCode(HttpStatus.OK.value());
        }
        return responseModel;
    }

    public void saveWebActionDBEntity(TRCDataManagementEntity trcDataManagementEntity) {
        Optional<String> optional = Optional.ofNullable(trcDataManagementEntity.getTransactionId());
        if (optional.isPresent()) {
            String txnID = optional.get();
            logger.info("txnID [" + txnID + "]");
            String requestType = trcDataManagementEntity.getRequestType();
            logger.info("requestType [" + requestType + "]");
            WebActionDBEntity webActionDBEntity = new WebActionDBEntity().setFeature(FeaturesEnum.getFeatureName(requestType)).setSubFeature(requestType).setState(WebActionDbState.PROCESSING.getCode()).setTxnId(txnID);
            logger.info("WebActionDBEntity payload[" + webActionDBEntity + "]");
            webActionDBRepository.save(webActionDBEntity);
        }
    }
}
