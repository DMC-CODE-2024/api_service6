package app.apiservice.features.notification.paging;

import app.apiservice.common.db.PropertiesReader;
import app.apiservice.common.enums.Datatype;
import app.apiservice.common.enums.SearchOperation;
import app.apiservice.common.exceptions.ResourceServicesException;
import app.apiservice.common.specificationsbuilder.GenericSpecificationBuilder;
import app.apiservice.common.specificationsbuilder.SearchCriteria;
import app.apiservice.common.specificationsbuilder.SortDirection;
import app.apiservice.entity.app.NotificationEntity;
import app.apiservice.entity.app.SysParamListValueEntity;
import app.apiservice.features.trc.model.AuditTrailModel;
import app.apiservice.features.trc.service.HelperUtility;
import app.apiservice.repository.app.NotificationRepository;
import app.apiservice.repository.app.SysParamListRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
public class NotificationPaging {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private PropertiesReader propertiesReader;
    private NotificationRepository notificationRepository;
    private HelperUtility helperUtility;

    private SysParamListRepository sysParamListRepository;

    public NotificationPaging(NotificationRepository notificationRepository, HelperUtility helperUtility, SysParamListRepository sysParamListRepository) {
        this.notificationRepository = notificationRepository;
        this.helperUtility = helperUtility;
        this.sysParamListRepository = sysParamListRepository;
    }


    public Page<NotificationEntity> findAll(NotificationEntity notificationEntity, int pageNo, int pageSize) {

        try {
            logger.info("request received : " + notificationEntity + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);
            String sort = null, orderColumn = null;
            if (Objects.nonNull(notificationEntity.getAuditTrailModel())) {
                sort = Objects.nonNull(notificationEntity.getAuditTrailModel().getSort()) ? notificationEntity.getAuditTrailModel().getSort() : "desc";
                orderColumn = Objects.nonNull(notificationEntity.getAuditTrailModel().getColumnName()) ? notificationEntity.getAuditTrailModel().getColumnName() : "Modified On";
            } else {
                sort = "desc";
                orderColumn = "Modified On";
            }
            orderColumn = sortColumnName(orderColumn);
            Sort.Direction direction = SortDirection.getSortDirection(sort);
            logger.info("request received : " + notificationEntity + " [pageNo] ...." + pageNo + " [pageSize]...." + pageSize);

            logger.info("orderColumn is : " + orderColumn + " & direction is : " + direction);

            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, orderColumn));
            Page<NotificationEntity> page = notificationRepository.findAll(buildSpecification(notificationEntity).build(), pageable);

  /*          for (NotificationEntity notificationEntityDetail : page.getContent()) {
                String interpretation = getInterpretationByStateAndTag(String.valueOf(notificationEntityDetail.getStatus()));
                logger.info("interpretation {}", interpretation);
                notificationEntityDetail.setInterp(interpretation);
            }*/

            logger.info("paging API response [" + page + "]");
            return page;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
        }
    }

    public NotificationEntity findById(Long id) {
        boolean isExist = notificationRepository.existsById(id);
        if (isExist) {
            Optional<NotificationEntity> result = notificationRepository.findById(id);
            String interpretation = getInterpretationByStateAndTag(String.valueOf(result.get().getStatus()));
            result.get().setInterp(interpretation);
            logger.info("record found : {}", result);
            return result.get();
        }
        logger.info("no record found for id {}", id);
        return null;
    }

    public String getInterpretationByStateAndTag(String value) {
        SysParamListValueEntity actionState = sysParamListRepository.findByTagAndValue("ACTION_STATE", value);
        return actionState != null ? actionState.getInterpretation() : null;
    }


    public String sortColumnName(String columnName) {
        Map<String, String> map = new HashMap<>();
        if (Objects.nonNull(columnName) && !columnName.isEmpty()) {
            map.put("Creation Date", "createdOn");
            map.put("Channel Type", "channelType");
            map.put("Modified On", "modifiedOn");
            map.put("Status", "status");
            map.put("Feature Name", "featureName");
            map.put("Feature Txn ID", "featureTxnId");
            map.put("Language", "msgLang");
            map.put("Message", "message");
            map.put("Notification Sent Time", "notificationSentTime");
            map.put("MSISDN", "msisdn");
            map.put("Email", "email");
            map.put("Subject", "subject");
        }
        return map.get(columnName);
    }

    private GenericSpecificationBuilder<NotificationEntity> buildSpecification(NotificationEntity notificationEntity) {
        logger.info("FilterRequest payload : [" + notificationEntity + "]");
        GenericSpecificationBuilder<NotificationEntity> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

        Optional<AuditTrailModel> optional = Optional.ofNullable(notificationEntity.getAuditTrailModel());
        if (optional.isPresent()) {
            cmsb.with(new SearchCriteria("createdOn", optional.get().getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));
            cmsb.with(new SearchCriteria("createdOn", optional.get().getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));
        }
        cmsb.with(new SearchCriteria("id", notificationEntity.getId(), SearchOperation.EQUALITY, Datatype.LONG));
        cmsb.with(new SearchCriteria("channelType", notificationEntity.getChannelType(), SearchOperation.EQUALITY, Datatype.STRING));
        cmsb.with(new SearchCriteria("featureId", notificationEntity.getFeatureId(), SearchOperation.EQUALITY, Datatype.INT));
        cmsb.with(new SearchCriteria("featureName", notificationEntity.getFeatureName(), SearchOperation.EQUALITY, Datatype.STRING));
        cmsb.with(new SearchCriteria("featureTxnId", notificationEntity.getFeatureTxnId(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("msgLang", notificationEntity.getMsgLang(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("message", notificationEntity.getMessage(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("receiverUserType", notificationEntity.getReceiverUserType(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("referTable", notificationEntity.getReferTable(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("retryCount", notificationEntity.getRetryCount(), SearchOperation.LIKE, Datatype.INT));
        cmsb.with(new SearchCriteria("roleType", notificationEntity.getRoleType(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("status", notificationEntity.getStatus(), SearchOperation.EQUALITY, Datatype.INT));
        cmsb.with(new SearchCriteria("subFeature", notificationEntity.getSubFeature(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("subject", notificationEntity.getSubject(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("userId", notificationEntity.getUserId(), SearchOperation.EQUALITY, Datatype.INT));
        cmsb.with(new SearchCriteria("notificationSentTime", notificationEntity.getNotificationSentTime(), SearchOperation.LIKE, Datatype.DATE));
        cmsb.with(new SearchCriteria("operatorName", notificationEntity.getOperatorName(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("msisdn", notificationEntity.getMsisdn(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("email", notificationEntity.getEmail(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("corelationId", notificationEntity.getCorelationId(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("deliveryStatus", notificationEntity.getDeliveryStatus(), SearchOperation.EQUALITY, Datatype.INT));
        cmsb.with(new SearchCriteria("deliveryTime", notificationEntity.getDeliveryTime(), SearchOperation.LIKE, Datatype.DATE));
        cmsb.with(new SearchCriteria("sendSmsInterface", notificationEntity.getSendSmsInterface(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("attachment", notificationEntity.getAttachment(), SearchOperation.LIKE, Datatype.STRING));
        cmsb.with(new SearchCriteria("checkImeiId", notificationEntity.getCheckImeiId(), SearchOperation.LIKE, Datatype.INT));

        return cmsb;
    }

}
