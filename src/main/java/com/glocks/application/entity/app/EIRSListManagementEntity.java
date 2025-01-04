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
@Table(name = "eirs_list_mgmt")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EIRSListManagementEntity implements Serializable {
    private static final long serialVersionUID = 2814641526479522558L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "imei")
    private String imei;

    @Column(name = "imsi")
    private String imsi;
    @Column(name = "tac")
    private String tac;


    @Column(name = "file_name")
    private String fileName;

    @Column(name = "request_mode")
    private String requestMode;


    @Column(name = "remark")
    private String remarks;


    @Column(name = "status")
    private String status;

    @Column(name = "category")
    private String category;

    @Column(name = "transaction_id")
    private String transactionId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonProperty(value = "user", access = JsonProperty.Access.READ_ONLY)
    private User user;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "total_count",updatable = false,insertable = false)
    private Long quantity;
    @Column(name = "action")
    private String action;
    @Column(name = "request_type")
    private String requestType;

/*    @Column(name = "added_by")
    private String addedBy;*/

    @Transient
    private String uploadedBy;
    @Transient
    @JsonProperty(value = "auditTrailModel", access = JsonProperty.Access.WRITE_ONLY)
    private AuditTrailModel auditTrailModel;

    public String getUploadedBy() {
        return uploadedBy;
    }

    public EIRSListManagementEntity setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
        return this;
    }

/*    public String getAddedBy() {
        return addedBy;
    }

    public EIRSListManagementEntity setAddedBy(String addedBy) {
        this.addedBy = addedBy;
        return this;
    }*/

    public Long getId() {
        return id;
    }

    public EIRSListManagementEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public EIRSListManagementEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public EIRSListManagementEntity setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public EIRSListManagementEntity setMsisdn(String msisdn) {
        this.msisdn = msisdn;
        return this;
    }

    public String getImei() {
        return imei;
    }

    public EIRSListManagementEntity setImei(String imei) {
        this.imei = imei;
        return this;
    }

    public String getImsi() {
        return imsi;
    }

    public EIRSListManagementEntity setImsi(String imsi) {
        this.imsi = imsi;
        return this;
    }

    public String getTac() {
        return tac;
    }

    public EIRSListManagementEntity setTac(String tac) {
        this.tac = tac;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public EIRSListManagementEntity setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getRequestMode() {
        return requestMode;
    }

    public EIRSListManagementEntity setRequestMode(String requestMode) {
        this.requestMode = requestMode;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public EIRSListManagementEntity setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public EIRSListManagementEntity setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public EIRSListManagementEntity setCategory(String category) {
        this.category = category;
        return this;
    }


    public User getUser() {
        return user;
    }

    public EIRSListManagementEntity setUser(User user) {
        this.user = user;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getAction() {
        return action;
    }

    public EIRSListManagementEntity setAction(String action) {
        this.action = action;
        return this;
    }

    public String getRequestType() {
        return requestType;
    }

    public EIRSListManagementEntity setRequestType(String requestType) {
        this.requestType = requestType;
        return this;
    }

    public AuditTrailModel getAuditTrailModel() {
        return auditTrailModel;
    }

    public EIRSListManagementEntity setAuditTrailModel(AuditTrailModel auditTrailModel) {
        this.auditTrailModel = auditTrailModel;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public EIRSListManagementEntity setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public EIRSListManagementEntity setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EIRSListManagementEntity{");
        sb.append("id=").append(id);
        sb.append(", createdOn=").append(createdOn);
        sb.append(", modifiedOn=").append(modifiedOn);
        sb.append(", msisdn='").append(msisdn).append('\'');
        sb.append(", imei='").append(imei).append('\'');
        sb.append(", imsi='").append(imsi).append('\'');
        sb.append(", tac='").append(tac).append('\'');
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append(", requestMode='").append(requestMode).append('\'');
        sb.append(", remarks='").append(remarks).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append(", transactionId='").append(transactionId).append('\'');
        sb.append(", user=").append(user);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", action='").append(action).append('\'');
        sb.append(", requestType='").append(requestType).append('\'');
        sb.append(", uploadedBy='").append(uploadedBy).append('\'');
        sb.append(", auditTrailModel=").append(auditTrailModel);
        sb.append('}');
        return sb.toString();
    }
}
