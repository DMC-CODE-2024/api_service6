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
@Table(name = "blocked_tac_list")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BlockTACListEntity implements Serializable {
    private static final long serialVersionUID = 4634644827324074556L;
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

    @Column(name = "tac")
    private String tac;
    @Column(name = "source")
    private String source;
    @Column(name = "remark")
    private String remarks;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "txn_id")
    private String txnId;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    @JsonProperty(value = "user", access = JsonProperty.Access.READ_ONLY)
    private User user;


    @Column(name = "mode_type")
    private String modeType;


    @Transient
    private String uploadedBy;
    @Transient
    @JsonProperty(value = "auditTrailModel", access = JsonProperty.Access.WRITE_ONLY)
    private AuditTrailModel auditTrailModel;

    public String getSource() {
        return source;
    }

    public BlockTACListEntity setSource(String source) {
        this.source = source;
        return this;
    }

    public String getModeType() {
        return modeType;
    }

    public BlockTACListEntity setModeType(String modeType) {
        this.modeType = modeType;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public BlockTACListEntity setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public Long getId() {
        return id;
    }

    public BlockTACListEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public BlockTACListEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public BlockTACListEntity setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public String getTac() {
        return tac;
    }

    public BlockTACListEntity setTac(String tac) {
        this.tac = tac;
        return this;
    }

    public String getRequestType() {
        return requestType;
    }

    public BlockTACListEntity setRequestType(String requestType) {
        this.requestType = requestType;
        return this;
    }

    public String getTxnId() {
        return txnId;
    }

    public BlockTACListEntity setTxnId(String txnId) {
        this.txnId = txnId;
        return this;
    }

    public User getUser() {
        return user;
    }

    public BlockTACListEntity setUser(User user) {
        this.user = user;
        return this;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public BlockTACListEntity setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
        return this;
    }

    public AuditTrailModel getAuditTrailModel() {
        return auditTrailModel;
    }

    public BlockTACListEntity setAuditTrailModel(AuditTrailModel auditTrailModel) {
        this.auditTrailModel = auditTrailModel;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BlockTACListEntity{");
        sb.append("id=").append(id);
        sb.append(", createdOn=").append(createdOn);
        sb.append(", modifiedOn=").append(modifiedOn);
        sb.append(", tac='").append(tac).append('\'');
        sb.append(", requestType='").append(requestType).append('\'');
        sb.append(", txnId='").append(txnId).append('\'');
        sb.append(", user=").append(user);
        sb.append(", source='").append(source).append('\'');
        sb.append(", modeType='").append(modeType).append('\'');
        sb.append(", remarks='").append(remarks).append('\'');
        sb.append(", uploadedBy='").append(uploadedBy).append('\'');
        sb.append(", auditTrailModel=").append(auditTrailModel);
        sb.append('}');
        return sb.toString();
    }
}

