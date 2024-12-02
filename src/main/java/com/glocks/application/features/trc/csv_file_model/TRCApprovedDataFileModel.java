package com.glocks.application.features.trc.csv_file_model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class TRCApprovedDataFileModel {
    @CsvBindByName(column = "Approved Date")
    @CsvBindByPosition(position = 0)
    private String approvedDate;
    @CsvBindByName(column = "Company")
    @CsvBindByPosition(position = 1)
    private String company;

    @CsvBindByName(column = "Company ID")
    @CsvBindByPosition(position = 2)
    private String companyId;


    @CsvBindByName(column = "Trademark")
    @CsvBindByPosition(position = 3)
    private String trademark;

    @CsvBindByName(column = "Product Name")
    @CsvBindByPosition(position = 4)
    private String productName;

    @CsvBindByName(column = "Commercial Name")
    @CsvBindByPosition(position = 5)
    private String commercialName;
    @CsvBindByName(column = "Model")
    @CsvBindByPosition(position = 6)
    private String model;
    @CsvBindByName(column = "Country Of Manufacture")
    @CsvBindByPosition(position = 7)
    private String country;

    @CsvBindByName(column = "TRC Identifier")
    @CsvBindByPosition(position = 8)
    private String trcIdentifier;

    public String getApprovedDate() {
        return approvedDate;
    }

    public TRCApprovedDataFileModel setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public TRCApprovedDataFileModel setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getCompanyId() {
        return companyId;
    }

    public TRCApprovedDataFileModel setCompanyId(String companyId) {
        this.companyId = companyId;
        return this;
    }

    public String getTrademark() {
        return trademark;
    }

    public TRCApprovedDataFileModel setTrademark(String trademark) {
        this.trademark = trademark;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public TRCApprovedDataFileModel setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public TRCApprovedDataFileModel setCommercialName(String commercialName) {
        this.commercialName = commercialName;
        return this;
    }

    public String getModel() {
        return model;
    }

    public TRCApprovedDataFileModel setModel(String model) {
        this.model = model;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public TRCApprovedDataFileModel setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getTrcIdentifier() {
        return trcIdentifier;
    }

    public TRCApprovedDataFileModel setTrcIdentifier(String trcIdentifier) {
        this.trcIdentifier = trcIdentifier;
        return this;
    }

    @Override
    public String toString() {
        return "TRCApprovedDataFileModel{" +
                "approvedDate='" + approvedDate + '\'' +
                ", company='" + company + '\'' +
                ", companyId='" + companyId + '\'' +
                ", trademark='" + trademark + '\'' +
                ", productName='" + productName + '\'' +
                ", commercialName='" + commercialName + '\'' +
                ", model='" + model + '\'' +
                ", country='" + country + '\'' +
                ", trcIdentifier='" + trcIdentifier + '\'' +
                '}';
    }
}
