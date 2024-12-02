package com.glocks.application.features.listmanagement.csv_file_model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class EIRSListManagementFileModel {
    @CsvBindByName(column = "Date")
    @CsvBindByPosition(position = 0)
    private String createdOn;
    @CsvBindByName(column = "Transaction ID")
    @CsvBindByPosition(position = 1)
    private String transactionId;
    @CsvBindByName(column = "Mode")
    @CsvBindByPosition(position = 2)
    private String mode;
    @CsvBindByName(column = "Status")
    @CsvBindByPosition(position = 3)
    private String status;

    @CsvBindByName(column = "Request Type")
    @CsvBindByPosition(position = 4)
    private String request;

    @CsvBindByName(column = "Category")
    @CsvBindByPosition(position = 5)
    private String category;
    @CsvBindByName(column = "Uploaded By")
    @CsvBindByPosition(position = 6)
    private String addedBy;

    public String getCategory() {
        return category;
    }

    public EIRSListManagementFileModel setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public EIRSListManagementFileModel setAddedBy(String addedBy) {
        this.addedBy = addedBy;
        return this;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public EIRSListManagementFileModel setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public EIRSListManagementFileModel setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getMode() {
        return mode;
    }

    public EIRSListManagementFileModel setMode(String mode) {
        this.mode = mode;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public EIRSListManagementFileModel setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getRequest() {
        return request;
    }

    public EIRSListManagementFileModel setRequest(String request) {
        this.request = request;
        return this;
    }
}
