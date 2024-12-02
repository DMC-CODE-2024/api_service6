package com.glocks.application.features.listmanagement.csv_file_model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class BlockTACFileModel {
    @CsvBindByName(column = "Date")
    @CsvBindByPosition(position = 0)
    private String createdOn;
    @CsvBindByName(column = "TAC")
    @CsvBindByPosition(position = 1)
    private String tac;

    @CsvBindByName(column = "Transaction ID")
    @CsvBindByPosition(position = 2)
    private String txnId;


    @CsvBindByName(column = "Category")
    @CsvBindByPosition(position = 3)
    private String category;

    @CsvBindByName(column = "Uploaded By")
    @CsvBindByPosition(position = 4)
    private String uploadedBy;
    @CsvBindByName(column = "Source")
    @CsvBindByPosition(position = 5)
    private String source;

    public String getSource() {
        return source;
    }

    public BlockTACFileModel setSource(String source) {
        this.source = source;
        return this;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public BlockTACFileModel setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public String getTac() {
        return tac;
    }

    public BlockTACFileModel setTac(String tac) {
        this.tac = tac;
        return this;
    }

    public String getTxnId() {
        return txnId;
    }

    public BlockTACFileModel setTxnId(String txnId) {
        this.txnId = txnId;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public BlockTACFileModel setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public BlockTACFileModel setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
        return this;
    }
}
