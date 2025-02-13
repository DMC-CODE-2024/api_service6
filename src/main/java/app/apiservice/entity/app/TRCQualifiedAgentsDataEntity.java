package app.apiservice.entity.app;

import app.apiservice.common.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import app.apiservice.features.trc.model.AuditTrailModel;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "trc_qualified_agent_data")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TRCQualifiedAgentsDataEntity implements Serializable {
    private static final long serialVersionUID = -8315927738639639121L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(hidden = true)
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Schema(hidden = true)
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @Column(name = "no")
    private Integer no;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "company_id")
    private String companyId;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;

    //@JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "expiry_date")
    private String expiryDate;
    @Transient
   // @JsonProperty(value = "auditTrailModel", access = JsonProperty.Access.WRITE_ONLY)
    private AuditTrailModel auditTrailModel;


    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TRCQualifiedAgentsDataEntity{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                ", modifiedOn=" + modifiedOn +
                ", no=" + no +
                ", companyName='" + companyName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", auditTrailModel=" + auditTrailModel +
                ", companyId='" + companyId + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                '}';
    }

    public TRCQualifiedAgentsDataEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public TRCQualifiedAgentsDataEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public TRCQualifiedAgentsDataEntity setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public Integer getNo() {
        return no;
    }

    public TRCQualifiedAgentsDataEntity setNo(Integer no) {
        this.no = no;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public TRCQualifiedAgentsDataEntity setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public TRCQualifiedAgentsDataEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public TRCQualifiedAgentsDataEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public AuditTrailModel getAuditTrailModel() {
        return auditTrailModel;
    }

    public TRCQualifiedAgentsDataEntity setAuditTrailModel(AuditTrailModel auditTrailModel) {
        this.auditTrailModel = auditTrailModel;
        return this;
    }

    public String getCompanyId() {
        return companyId;
    }

    public TRCQualifiedAgentsDataEntity setCompanyId(String companyId) {
        this.companyId = companyId;
        return this;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public TRCQualifiedAgentsDataEntity setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

}
