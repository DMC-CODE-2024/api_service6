package com.glocks.application.entity.app;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "duplicate_device_detail")
public class DuplicateDeviceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_on")
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    @Column(name = "modified_on")
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedOn;

    @Column(name = "imei")
    private String imei;

    @Column(name = "imsi")
    private String imsi;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "edr_time")
    private LocalDateTime edrTime;

    @Column(name = "operator")
    private String operator;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    @Column(name = "remark")
    private String remarks;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "document_type1")
    private String documentType1;

    @Column(name = "document_type2")
    private String documentType2;

    @Column(name = "document_type3")
    private String documentType3;

    @Column(name = "document_type4")
    private String documentType4;

    @Column(name = "document_file_name_1")
    private String documentFileName1;

    @Column(name = "document_file_name_2")
    private String documentFileName2;

    @Column(name = "document_file_name_3")
    private String documentFileName3;

    @Column(name = "document_file_name_4")
    private String documentFileName4;

    @Column(name = "reminder_status")
    private Integer reminderStatus;

    @Column(name = "success_count")
    private Integer successCount;

    @Column(name = "fail_count")
    private Integer failCount;

    @Column(name = "approve_transaction_id")
    private String approveTransactionId;

    @Column(name = "approve_remark")
    private String approveRemark;

    @Column(name = "document_path1")
    private String documentPath1;

    @Column(name = "document_path2")
    private String documentPath2;

    @Column(name = "document_path3")
    private String documentPath3;

    @Column(name = "document_path4")
    private String documentPath4;

    @Column(name = "actual_imei")
    private String actualImei;

    @Column(name = "status")
    private String status;

    public Long getId() {
        return id;
    }

    public DuplicateDeviceDetail setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public DuplicateDeviceDetail setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public DuplicateDeviceDetail setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public String getImei() {
        return imei;
    }

    public DuplicateDeviceDetail setImei(String imei) {
        this.imei = imei;
        return this;
    }

    public String getImsi() {
        return imsi;
    }

    public DuplicateDeviceDetail setImsi(String imsi) {
        this.imsi = imsi;
        return this;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public DuplicateDeviceDetail setMsisdn(String msisdn) {
        this.msisdn = msisdn;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public DuplicateDeviceDetail setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public LocalDateTime getEdrTime() {
        return edrTime;
    }

    public DuplicateDeviceDetail setEdrTime(LocalDateTime edrTime) {
        this.edrTime = edrTime;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public DuplicateDeviceDetail setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public DuplicateDeviceDetail setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public DuplicateDeviceDetail setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public DuplicateDeviceDetail setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public DuplicateDeviceDetail setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getDocumentType1() {
        return documentType1;
    }

    public DuplicateDeviceDetail setDocumentType1(String documentType1) {
        this.documentType1 = documentType1;
        return this;
    }

    public String getDocumentType2() {
        return documentType2;
    }

    public DuplicateDeviceDetail setDocumentType2(String documentType2) {
        this.documentType2 = documentType2;
        return this;
    }

    public String getDocumentType3() {
        return documentType3;
    }

    public DuplicateDeviceDetail setDocumentType3(String documentType3) {
        this.documentType3 = documentType3;
        return this;
    }

    public String getDocumentType4() {
        return documentType4;
    }

    public DuplicateDeviceDetail setDocumentType4(String documentType4) {
        this.documentType4 = documentType4;
        return this;
    }

    public String getDocumentFileName1() {
        return documentFileName1;
    }

    public DuplicateDeviceDetail setDocumentFileName1(String documentFileName1) {
        this.documentFileName1 = documentFileName1;
        return this;
    }

    public String getDocumentFileName2() {
        return documentFileName2;
    }

    public DuplicateDeviceDetail setDocumentFileName2(String documentFileName2) {
        this.documentFileName2 = documentFileName2;
        return this;
    }

    public String getDocumentFileName3() {
        return documentFileName3;
    }

    public DuplicateDeviceDetail setDocumentFileName3(String documentFileName3) {
        this.documentFileName3 = documentFileName3;
        return this;
    }

    public String getDocumentFileName4() {
        return documentFileName4;
    }

    public DuplicateDeviceDetail setDocumentFileName4(String documentFileName4) {
        this.documentFileName4 = documentFileName4;
        return this;
    }

    public Integer getReminderStatus() {
        return reminderStatus;
    }

    public DuplicateDeviceDetail setReminderStatus(Integer reminderStatus) {
        this.reminderStatus = reminderStatus;
        return this;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public DuplicateDeviceDetail setSuccessCount(Integer successCount) {
        this.successCount = successCount;
        return this;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public DuplicateDeviceDetail setFailCount(Integer failCount) {
        this.failCount = failCount;
        return this;
    }

    public String getApproveTransactionId() {
        return approveTransactionId;
    }

    public DuplicateDeviceDetail setApproveTransactionId(String approveTransactionId) {
        this.approveTransactionId = approveTransactionId;
        return this;
    }

    public String getApproveRemark() {
        return approveRemark;
    }

    public DuplicateDeviceDetail setApproveRemark(String approveRemark) {
        this.approveRemark = approveRemark;
        return this;
    }

    public String getDocumentPath1() {
        return documentPath1;
    }

    public DuplicateDeviceDetail setDocumentPath1(String documentPath1) {
        this.documentPath1 = documentPath1;
        return this;
    }

    public String getDocumentPath2() {
        return documentPath2;
    }

    public DuplicateDeviceDetail setDocumentPath2(String documentPath2) {
        this.documentPath2 = documentPath2;
        return this;
    }

    public String getDocumentPath3() {
        return documentPath3;
    }

    public DuplicateDeviceDetail setDocumentPath3(String documentPath3) {
        this.documentPath3 = documentPath3;
        return this;
    }

    public String getDocumentPath4() {
        return documentPath4;
    }

    public DuplicateDeviceDetail setDocumentPath4(String documentPath4) {
        this.documentPath4 = documentPath4;
        return this;
    }

    public String getActualImei() {
        return actualImei;
    }

    public DuplicateDeviceDetail setActualImei(String actualImei) {
        this.actualImei = actualImei;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public DuplicateDeviceDetail setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DuplicateDeviceDetail{");
        sb.append("id=").append(id);
        sb.append(", createdOn=").append(createdOn);
        sb.append(", modifiedOn=").append(modifiedOn);
        sb.append(", imei='").append(imei).append('\'');
        sb.append(", imsi='").append(imsi).append('\'');
        sb.append(", msisdn='").append(msisdn).append('\'');
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append(", edrTime=").append(edrTime);
        sb.append(", operator='").append(operator).append('\'');
        sb.append(", expiryDate=").append(expiryDate);
        sb.append(", remarks='").append(remarks).append('\'');
        sb.append(", updatedBy='").append(updatedBy).append('\'');
        sb.append(", transactionId='").append(transactionId).append('\'');
        sb.append(", documentType1='").append(documentType1).append('\'');
        sb.append(", documentType2='").append(documentType2).append('\'');
        sb.append(", documentType3='").append(documentType3).append('\'');
        sb.append(", documentType4='").append(documentType4).append('\'');
        sb.append(", documentFileName1='").append(documentFileName1).append('\'');
        sb.append(", documentFileName2='").append(documentFileName2).append('\'');
        sb.append(", documentFileName3='").append(documentFileName3).append('\'');
        sb.append(", documentFileName4='").append(documentFileName4).append('\'');
        sb.append(", reminderStatus=").append(reminderStatus);
        sb.append(", successCount=").append(successCount);
        sb.append(", failCount=").append(failCount);
        sb.append(", approveTransactionId='").append(approveTransactionId).append('\'');
        sb.append(", approveRemark='").append(approveRemark).append('\'');
        sb.append(", documentPath1='").append(documentPath1).append('\'');
        sb.append(", documentPath2='").append(documentPath2).append('\'');
        sb.append(", documentPath3='").append(documentPath3).append('\'');
        sb.append(", documentPath4='").append(documentPath4).append('\'');
        sb.append(", actualImei='").append(actualImei).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

