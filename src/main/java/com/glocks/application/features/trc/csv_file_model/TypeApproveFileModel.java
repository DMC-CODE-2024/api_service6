package com.glocks.application.features.trc.csv_file_model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class TypeApproveFileModel {
    @CsvBindByName(column = "Updated Date")
    @CsvBindByPosition(position = 0)
    private String createdOn;
    @CsvBindByName(column = "File Name")
    @CsvBindByPosition(position = 1)
    private String fileName;

    @CsvBindByName(column = "Status")
    @CsvBindByPosition(position = 2)
    private String status;
    @CsvBindByName(column = "Transaction ID")
    @CsvBindByPosition(position = 3)
    private String transactionId;
    @CsvBindByName(column = "Uploaded By")
    @CsvBindByPosition(position = 4)
    private String uploadedBy;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public TypeApproveFileModel setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
        return this;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TypeApproveFileModel{");
        sb.append("createdOn='").append(createdOn).append('\'');
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append(", transactionId='").append(transactionId).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", uploadedBy='").append(uploadedBy).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
