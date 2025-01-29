package app.apiservice.entity.app;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import app.apiservice.features.trc.model.AuditTrailModel;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "channel_type")
    private String channelType;
    @Schema(hidden = true)
    @Column(name = "created_on")
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime createdOn;
    @Schema(hidden = true)
    @Column(name = "modified_on")
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime modifiedOn;

    @Column(name = "feature_id")
    private Integer featureId;

    @Column(name = "feature_name")
    private String featureName;

    @Column(name = "feature_txn_id")
    private String featureTxnId;

    @Column(name = "msg_lang")
    private String msgLang;

    @Column(name = "message")
    private String message;


    @Column(name = "receiver_user_type")
    private String receiverUserType;

    @Column(name = "refer_table")
    private String referTable;

    @Column(name = "retry_count")
    private Integer retryCount;

    @Column(name = "role_type")
    private String roleType;

    @Column(name = "status")
    private Integer status;

    @Column(name = "sub_feature")
    private String subFeature;

    @Column(name = "subject")
    private String subject;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "notification_sent_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime notificationSentTime;

    @Column(name = "operator_name")
    private String operatorName;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "email")
    private String email;

    @Column(name = "corelation_id")
    private String corelationId;

    @Column(name = "delivery_status")
    private Integer deliveryStatus;

    @Column(name = "delivery_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime deliveryTime;

    @Column(name = "send_sms_interface")
    private String sendSmsInterface;

    @Column(name = "attachment")
    private String attachment;

/*
    @Column(name = "sms_scheduled_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime smsScheduledTime;
*/


    @Column(name = "check_imei_id")
    private Integer checkImeiId;

    @Transient
    @JsonProperty(value = "auditTrailModel", access = JsonProperty.Access.WRITE_ONLY)
    private AuditTrailModel auditTrailModel;
    // Constructors, getters, and setters

    @Transient
    private String interp;

    public String getInterp() {
        return interp;
    }

    public NotificationEntity setInterp(String interp) {
        this.interp = interp;
        return this;
    }

    public Long getId() {
        return id;
    }

    public NotificationEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getChannelType() {
        return channelType;
    }

    public NotificationEntity setChannelType(String channelType) {
        this.channelType = channelType;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public NotificationEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public NotificationEntity setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public Integer getFeatureId() {
        return featureId;
    }

    public NotificationEntity setFeatureId(Integer featureId) {
        this.featureId = featureId;
        return this;
    }

    public String getFeatureName() {
        return featureName;
    }

    public NotificationEntity setFeatureName(String featureName) {
        this.featureName = featureName;
        return this;
    }

    public String getFeatureTxnId() {
        return featureTxnId;
    }

    public NotificationEntity setFeatureTxnId(String featureTxnId) {
        this.featureTxnId = featureTxnId;
        return this;
    }

    public String getMsgLang() {
        return msgLang;
    }

    public NotificationEntity setMsgLang(String msgLang) {
        this.msgLang = msgLang;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public NotificationEntity setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getReceiverUserType() {
        return receiverUserType;
    }

    public NotificationEntity setReceiverUserType(String receiverUserType) {
        this.receiverUserType = receiverUserType;
        return this;
    }

    public String getReferTable() {
        return referTable;
    }

    public NotificationEntity setReferTable(String referTable) {
        this.referTable = referTable;
        return this;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public NotificationEntity setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    public String getRoleType() {
        return roleType;
    }

    public NotificationEntity setRoleType(String roleType) {
        this.roleType = roleType;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public NotificationEntity setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getSubFeature() {
        return subFeature;
    }

    public NotificationEntity setSubFeature(String subFeature) {
        this.subFeature = subFeature;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public NotificationEntity setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public NotificationEntity setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public LocalDateTime getNotificationSentTime() {
        return notificationSentTime;
    }

    public NotificationEntity setNotificationSentTime(LocalDateTime notificationSentTime) {
        this.notificationSentTime = notificationSentTime;
        return this;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public NotificationEntity setOperatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public NotificationEntity setMsisdn(String msisdn) {
        this.msisdn = msisdn;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public NotificationEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCorelationId() {
        return corelationId;
    }

    public NotificationEntity setCorelationId(String corelationId) {
        this.corelationId = corelationId;
        return this;
    }

    public Integer getDeliveryStatus() {
        return deliveryStatus;
    }

    public NotificationEntity setDeliveryStatus(Integer deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public NotificationEntity setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
        return this;
    }

    public String getSendSmsInterface() {
        return sendSmsInterface;
    }

    public NotificationEntity setSendSmsInterface(String sendSmsInterface) {
        this.sendSmsInterface = sendSmsInterface;
        return this;
    }

    public String getAttachment() {
        return attachment;
    }

    public NotificationEntity setAttachment(String attachment) {
        this.attachment = attachment;
        return this;
    }

    public Integer getCheckImeiId() {
        return checkImeiId;
    }

    public NotificationEntity setCheckImeiId(Integer checkImeiId) {
        this.checkImeiId = checkImeiId;
        return this;
    }

    public AuditTrailModel getAuditTrailModel() {
        return auditTrailModel;
    }

    public NotificationEntity setAuditTrailModel(AuditTrailModel auditTrailModel) {
        this.auditTrailModel = auditTrailModel;
        return this;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NotificationEntity{");
        sb.append("id=").append(id);
        sb.append(", channelType='").append(channelType).append('\'');
        sb.append(", createdOn=").append(createdOn);
        sb.append(", modifiedOn=").append(modifiedOn);
        sb.append(", featureId=").append(featureId);
        sb.append(", featureName='").append(featureName).append('\'');
        sb.append(", featureTxnId='").append(featureTxnId).append('\'');
        sb.append(", msgLang='").append(msgLang).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", receiverUserType='").append(receiverUserType).append('\'');
        sb.append(", referTable='").append(referTable).append('\'');
        sb.append(", retryCount=").append(retryCount);
        sb.append(", roleType='").append(roleType).append('\'');
        sb.append(", status=").append(status);
        sb.append(", subFeature='").append(subFeature).append('\'');
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", notificationSentTime=").append(notificationSentTime);
        sb.append(", operatorName='").append(operatorName).append('\'');
        sb.append(", msisdn='").append(msisdn).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", corelationId='").append(corelationId).append('\'');
        sb.append(", deliveryStatus=").append(deliveryStatus);
        sb.append(", deliveryTime=").append(deliveryTime);
        sb.append(", sendSmsInterface='").append(sendSmsInterface).append('\'');
        sb.append(", attachment='").append(attachment).append('\'');
        sb.append(", checkImeiId=").append(checkImeiId);
        sb.append(", auditTrailModel=").append(auditTrailModel);
        sb.append(", interp='").append(interp).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
