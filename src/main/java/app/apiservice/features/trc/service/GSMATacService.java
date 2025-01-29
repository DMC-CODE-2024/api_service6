package app.apiservice.features.trc.service;

import app.apiservice.common.enums.FeaturesEnum;
import app.apiservice.common.exceptions.ResourceServicesException;
import app.apiservice.common.externalproperties.FeatureNameMap;
import app.apiservice.common.features.audit_trail.AuditTrailService;
import app.apiservice.common.model.FileDetails;
import app.apiservice.common.model.ResponseModel;
import app.apiservice.entity.app.MobileDeviceRepository;
import app.apiservice.features.trc.export.ApproveDeviceTACExport;
import app.apiservice.features.trc.interfaces.ApprovedDeviceTacI;
import app.apiservice.features.trc.model.UpdateTACStatusModel;
import app.apiservice.features.trc.paging.GSMATacPaging;
import app.apiservice.repository.app.MobileDeviceRepoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

@Service
public class GSMATacService {

    private GSMATacPaging gsmaTacPaging;
    private AuditTrailService auditTrailService;
    private ApproveDeviceTACExport approveDeviceTACExport;
    private final Logger logger = LogManager.getLogger(this.getClass());
    private MobileDeviceRepoRepository mobileDeviceRepoRepository;

    public GSMATacService(MobileDeviceRepoRepository mobileDeviceRepoRepository, GSMATacPaging gsmaTacPaging, AuditTrailService auditTrailService, ApproveDeviceTACExport approveDeviceTACExport) {
        this.gsmaTacPaging = gsmaTacPaging;
        this.auditTrailService = auditTrailService;
        this.approveDeviceTACExport = approveDeviceTACExport;
        this.mobileDeviceRepoRepository = mobileDeviceRepoRepository;
    }

    @Autowired
    private FeatureNameMap featureNameMap;
    String requestType = "APPROVE_DEVICE_TAC";
    public MappingJacksonValue paging(MobileDeviceRepository mobileDeviceRepository, int pageNo, int pageSize) {
        Page<MobileDeviceRepository> page = null;
        try {
            logger.info("requestType [" + requestType + "]");
            page = gsmaTacPaging.findAll(mobileDeviceRepository, pageNo, pageSize);
            auditTrailService.auditTrailOperation(mobileDeviceRepository.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("VIEWALL"));
        } catch (Exception e) {
            new ResourceServicesException(FeaturesEnum.APPROVE_DEVICE_TAC_VIEWALL.getFeature(), "failed to fetch record");
        }
        return new MappingJacksonValue(page);
    }

    public MappingJacksonValue export(MobileDeviceRepository mobileDeviceRepository) {
        String fileName;
        Optional<String> optional = Optional.ofNullable(mobileDeviceRepository.getTrcApprovedStatus());
        if (optional.isPresent() && optional.get().equals("approved")) {
            fileName = featureNameMap.get(requestType);
        } else {
            fileName = featureNameMap.get("APPROVED_DEVICE_TAC");
        }
        FileDetails export = approveDeviceTACExport.export(mobileDeviceRepository, fileName);
        auditTrailService.auditTrailOperation(mobileDeviceRepository.getAuditTrailModel(), fileName, featureNameMap.get("EXPORT"));
        return new MappingJacksonValue(export);
    }

    @Transactional(rollbackOn = {SQLException.class})
    public ResponseModel update(MobileDeviceRepository mobileDeviceRepository) {
        String action;
        if (Objects.nonNull(mobileDeviceRepository.getId()) && Objects.nonNull(mobileDeviceRepository.getAction())) {
            try {
                boolean isIdExist = mobileDeviceRepoRepository.existsById(mobileDeviceRepository.getId());
                if (isIdExist) {
                    if (mobileDeviceRepository.getAction().equalsIgnoreCase("APPROVED")) {
                        action = "Approved";
                    } else if (mobileDeviceRepository.getAction().equalsIgnoreCase("REJECT")) {
                        action = "Reject";
                    } else if (mobileDeviceRepository.getAction().equalsIgnoreCase("NULL")) {
                        action = null;
                    } else {
                        return new ResponseModel(HttpStatus.NO_CONTENT.value(), "Not a valid request");
                    }
                    mobileDeviceRepository.setAction(action);
                    ApprovedDeviceTacI approvedDeviceTacI = (x) -> mobileDeviceRepoRepository.update(x);
                    int status = approvedDeviceTacI.update(mobileDeviceRepository);
                    logger.info("updated status [" + status + "]");
                    if (status == 1) {
                        auditTrailService.auditTrailOperation(mobileDeviceRepository.getAuditTrailModel(), featureNameMap.get(requestType), featureNameMap.get("UPDATE"));
                        Optional<MobileDeviceRepository> byId = mobileDeviceRepoRepository.findById(mobileDeviceRepository.getId());
                        MobileDeviceRepository result = byId.get();
                        UpdateTACStatusModel updateTACStatusModel = new UpdateTACStatusModel()
                                .setTac(result.getDeviceId())
                                .setApprovedBy(result.getApprovedBy())
                                .setAction(result.getTrcApprovedStatus());
                        return new ResponseModel(HttpStatus.OK.value(), updateTACStatusModel);
                    } else {
                        return new ResponseModel(HttpStatus.EXPECTATION_FAILED.value(), "Updation failed!");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new ResponseModel(HttpStatus.EXPECTATION_FAILED.value(), "Not a Valid Request");


    }
}
