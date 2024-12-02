package com.glocks.application.entity.app;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.glocks.application.features.trc.model.AuditTrailModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "black_list")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BlockListEntity implements Serializable {
    private static final long serialVersionUID = 5207772475438569502L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_on")
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdOn;

    @Column(name = "modified_on")
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime modifiedOn;

    @Column(name = "complaint_type")
    private String complaintType;

    @Column(name = "expiry_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime expiryDate;
    @Column(name = "imei")
    private String imei;

    @Column(name = "mode_type")
    private String modeType;
    @Column(name = "request_type")
    private String requestType;
    @Column(name = "txn_id")
    private String txnId;

/*    @Column(name = "user_id")
    private String userId;*/

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    @JsonProperty(value = "user", access = JsonProperty.Access.READ_ONLY)
    private User user;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "operator_id")
    private String operatorId;
    @Column(name = "operator_name")
    private String operatorName;

    @Column(name = "actual_imei")
    private String actualImei;
    @Column(name = "tac")
    private String tac;
    @Column(name = "remark")
    private String remarks;
    @Column(name = "imsi")
    private String imsi;
    @Column(name = "msisdn")
    private String msisdn;
    @Column(name = "source")
    private String source;
    @Transient
    private String uploadedBy;
    @Transient
    @JsonProperty(value = "auditTrailModel", access = JsonProperty.Access.WRITE_ONLY)
    private AuditTrailModel auditTrailModel;

    public AuditTrailModel getAuditTrailModel() {
        return auditTrailModel;
    }

    public String getSource() {
        return source;
    }

    public BlockListEntity setSource(String source) {
        this.source = source;
        return this;
    }

    public BlockListEntity setAuditTrailModel(AuditTrailModel auditTrailModel) {
        this.auditTrailModel = auditTrailModel;
        return this;
    }

    public Long getId() {
        return id;
    }

    public BlockListEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public BlockListEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public BlockListEntity setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public BlockListEntity setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
        return this;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public BlockListEntity setComplaintType(String complaintType) {
        this.complaintType = complaintType;
        return this;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public BlockListEntity setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public String getTac() {
        return tac;
    }

    public BlockListEntity setTac(String tac) {
        this.tac = tac;
        return this;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public BlockListEntity setMsisdn(String msisdn) {
        this.msisdn = msisdn;
        return this;
    }

    public String getModeType() {
        return modeType;
    }

    public BlockListEntity setModeType(String modeType) {
        this.modeType = modeType;
        return this;
    }

    public String getImsi() {
        return imsi;
    }

    public BlockListEntity setImsi(String imsi) {
        this.imsi = imsi;
        return this;
    }

    public String getRequestType() {
        return requestType;
    }

    public BlockListEntity setRequestType(String requestType) {
        this.requestType = requestType;
        return this;
    }

    public String getTxnId() {
        return txnId;
    }

    public BlockListEntity setTxnId(String txnId) {
        this.txnId = txnId;
        return this;
    }



    public String getUserType() {
        return userType;
    }

    public BlockListEntity setUserType(String userType) {
        this.userType = userType;
        return this;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public BlockListEntity setOperatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public BlockListEntity setOperatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getActualImei() {
        return actualImei;
    }

    public BlockListEntity setActualImei(String actualImei) {
        this.actualImei = actualImei;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public BlockListEntity setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public String getImei() {
        return imei;
    }

    public BlockListEntity setImei(String imei) {
        this.imei = imei;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BlockListEntity{");
        sb.append("id=").append(id);
        sb.append(", createdOn=").append(createdOn);
        sb.append(", modifiedOn=").append(modifiedOn);
        sb.append(", complaintType='").append(complaintType).append('\'');
        sb.append(", expiryDate=").append(expiryDate);
        sb.append(", tac='").append(tac).append('\'');
        sb.append(", msisdn='").append(msisdn).append('\'');
        sb.append(", modeType='").append(modeType).append('\'');
        sb.append(", imsi='").append(imsi).append('\'');
        sb.append(", requestType='").append(requestType).append('\'');
        sb.append(", txnId='").append(txnId).append('\'');
        sb.append(", user=").append(user);
        sb.append(", userType='").append(userType).append('\'');
        sb.append(", operatorName='").append(operatorName).append('\'');
        sb.append(", operatorId='").append(operatorId).append('\'');
        sb.append(", actualImei='").append(actualImei).append('\'');
        sb.append(", remarks='").append(remarks).append('\'');
        sb.append(", imei='").append(imei).append('\'');
        sb.append(", uploadedBy='").append(uploadedBy).append('\'');
        sb.append(", auditTrailModel=").append(auditTrailModel);
        sb.append(", source='").append(source).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

