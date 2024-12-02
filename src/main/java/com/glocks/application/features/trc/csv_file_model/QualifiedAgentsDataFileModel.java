package com.glocks.application.features.trc.csv_file_model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class QualifiedAgentsDataFileModel {
    @CsvBindByName(column = "S. No")
    @CsvBindByPosition(position = 0)
    private String no;
    @CsvBindByName(column = "Company Name")
    @CsvBindByPosition(position = 1)
    private String companyName;

    @CsvBindByName(column = "Company ID")
    @CsvBindByPosition(position = 2)
    private String companyId;
    @CsvBindByName(column = "Phone Number")
    @CsvBindByPosition(position = 3)
    private String phoneNumber;

    @CsvBindByName(column = "Email")
    @CsvBindByPosition(position = 4)
    private String email;

    @CsvBindByName(column = "Expiry")
    @CsvBindByPosition(position = 5)
    private String expiryDate;

    public String getNo() {
        return no;
    }

    public QualifiedAgentsDataFileModel setNo(String no) {
        this.no = no;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public QualifiedAgentsDataFileModel setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getCompanyId() {
        return companyId;
    }

    public QualifiedAgentsDataFileModel setCompanyId(String companyId) {
        this.companyId = companyId;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public QualifiedAgentsDataFileModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public QualifiedAgentsDataFileModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public QualifiedAgentsDataFileModel setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    @Override
    public String toString() {
        return "QualifiedAgentsDataFileModel{" +
                "no='" + no + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyId='" + companyId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                '}';
    }
}
