package com.glocks.application.features.listmanagement.csv_file_model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class GrayListFileModel {
    @CsvBindByName(column = "Date")
    @CsvBindByPosition(position = 0)
    private String createdOn;
    @CsvBindByName(column = "IMEI")
    @CsvBindByPosition(position = 1)
    private String imei;
    @CsvBindByName(column = "MSISDN")
    @CsvBindByPosition(position = 2)
    private String msisdn;
    @CsvBindByName(column = "IMSI")
    @CsvBindByPosition(position = 3)
    private String imsi;

    @CsvBindByName(column = "Transaction ID")
    @CsvBindByPosition(position = 4)
    private String txnId;


    @CsvBindByName(column = "Category")
    @CsvBindByPosition(position = 5)
    private String category;

    @CsvBindByName(column = "Uploaded By")
    @CsvBindByPosition(position = 6)
    private String uploadedBy;
    @CsvBindByName(column = "Source")
    @CsvBindByPosition(position = 7)
    private String source;

    public String getCreatedOn() {
        return createdOn;
    }

    public String getSource() {
        return source;
    }

    public GrayListFileModel setSource(String source) {
        this.source = source;
        return this;
    }

    public GrayListFileModel setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public String getImei() {
        return imei;
    }

    public GrayListFileModel setImei(String imei) {
        this.imei = imei;
        return this;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public GrayListFileModel setMsisdn(String msisdn) {
        this.msisdn = msisdn;
        return this;
    }

    public String getImsi() {
        return imsi;
    }

    public GrayListFileModel setImsi(String imsi) {
        this.imsi = imsi;
        return this;
    }

    public String getTxnId() {
        return txnId;
    }

    public GrayListFileModel setTxnId(String txnId) {
        this.txnId = txnId;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public GrayListFileModel setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public GrayListFileModel setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
        return this;
    }
}
