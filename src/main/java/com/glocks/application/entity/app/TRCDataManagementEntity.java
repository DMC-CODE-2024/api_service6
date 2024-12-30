package com.glocks.application.entity.app;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.glocks.application.features.trc.model.AuditTrailModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "trc_data_mgmt")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TRCDataManagementEntity implements Serializable {
    private static final long serialVersionUID = -4520165433022417125L;
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
    @Column(name = "file_name")
    private String fileName;

    @Column(name = "status")
    private String status;

    @Column(name = "transaction_id")
    private String transactionId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id" ,insertable = false, updatable = false)
    @JsonProperty(value = "user", access = JsonProperty.Access.READ_ONLY)
    private User user;
    @Column(name = "user_id")
    private String userId;
    @Transient
    private String uploadedBy;

    @Column(name = "request_type")
    @NotNull(message = "Request type can not be null or blank")
    @NotBlank
    private String requestType;
    @Column(name = "remark")
    private String remarks;

    @Transient
    @JsonProperty(value = "auditTrailModel", access = JsonProperty.Access.WRITE_ONLY)
    private AuditTrailModel auditTrailModel;

    public Long getId() {
        return id;
    }

    public TRCDataManagementEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public TRCDataManagementEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public TRCDataManagementEntity setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public TRCDataManagementEntity setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TRCDataManagementEntity setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public TRCDataManagementEntity setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public User getUser() {
        return user;
    }

    public TRCDataManagementEntity setUser(User user) {
        this.user = user;
        return this;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public TRCDataManagementEntity setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
        return this;
    }

    public String getRequestType() {
        return requestType;
    }

    public TRCDataManagementEntity setRequestType(String requestType) {
        this.requestType = requestType;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public TRCDataManagementEntity setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public AuditTrailModel getAuditTrailModel() {
        return auditTrailModel;
    }

    public TRCDataManagementEntity setAuditTrailModel(AuditTrailModel auditTrailModel) {
        this.auditTrailModel = auditTrailModel;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public TRCDataManagementEntity setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TRCDataManagementEntity{");
        sb.append("id=").append(id);
        sb.append(", createdOn=").append(createdOn);
        sb.append(", modifiedOn=").append(modifiedOn);
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", transactionId='").append(transactionId).append('\'');
        sb.append(", user=").append(user);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", uploadedBy='").append(uploadedBy).append('\'');
        sb.append(", requestType='").append(requestType).append('\'');
        sb.append(", remarks='").append(remarks).append('\'');
        sb.append(", auditTrailModel=").append(auditTrailModel);
        sb.append('}');
        return sb.toString();
    }
}
