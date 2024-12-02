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
@Table(name = "trc_type_approved_data")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TRCTypeApprovedDataEntity implements Serializable {
    private static final long serialVersionUID = 6246256798806753425L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;
    @Column(name = "no")
    private Long no;

    @Column(name = "company")
    private String company;
    /*  New parameter added on 18-07-2024*/
    @Column(name = "company_id")
    private String companyId;
    @Column(name = "trademark")
    private String trademark;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "model")
    private String model;
    @Column(name = "trc_identifier")
    private String trcIdentifier;


    @Column(name = "commercial_name")
    private String commercialName;

    @Column(name = "country_of_manufacture")
    private String country;

    @Column(name = "approved_date")
    private String approvalDate;

    @Transient
    @JsonProperty(value = "auditTrailModel", access = JsonProperty.Access.WRITE_ONLY)
    private AuditTrailModel auditTrailModel;


    public AuditTrailModel getAuditTrailModel() {
        return auditTrailModel;
    }

    public TRCTypeApprovedDataEntity setAuditTrailModel(AuditTrailModel auditTrailModel) {
        this.auditTrailModel = auditTrailModel;
        return this;
    }


    public Long getId() {
        return id;
    }

    public TRCTypeApprovedDataEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public TRCTypeApprovedDataEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public TRCTypeApprovedDataEntity setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public Long getNo() {
        return no;
    }

    public TRCTypeApprovedDataEntity setNo(Long no) {
        this.no = no;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public TRCTypeApprovedDataEntity setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getTrademark() {
        return trademark;
    }

    public TRCTypeApprovedDataEntity setTrademark(String trademark) {
        this.trademark = trademark;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public TRCTypeApprovedDataEntity setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getModel() {
        return model;
    }

    public TRCTypeApprovedDataEntity setModel(String model) {
        this.model = model;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public TRCTypeApprovedDataEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getTrcIdentifier() {
        return trcIdentifier;
    }

    public TRCTypeApprovedDataEntity setTrcIdentifier(String trcIdentifier) {
        this.trcIdentifier = trcIdentifier;
        return this;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public TRCTypeApprovedDataEntity setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
        return this;
    }

    public String getCompanyId() {
        return companyId;
    }

    public TRCTypeApprovedDataEntity setCompanyId(String companyId) {
        this.companyId = companyId;
        return this;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public TRCTypeApprovedDataEntity setCommercialName(String commercialName) {
        this.commercialName = commercialName;
        return this;
    }

    @Override
    public String toString() {
        return "TRCTypeApprovedDataEntity{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                ", modifiedOn=" + modifiedOn +
                ", no=" + no +
                ", company='" + company + '\'' +
                ", trademark='" + trademark + '\'' +
                ", productName='" + productName + '\'' +
                ", model='" + model + '\'' +
                ", country='" + country + '\'' +
                ", trcIdentifier='" + trcIdentifier + '\'' +
                ", approvalDate='" + approvalDate + '\'' +
                ", auditTrailModel=" + auditTrailModel +
                ", companyId='" + companyId + '\'' +
                ", commercialName='" + commercialName + '\'' +
                '}';
    }
}
