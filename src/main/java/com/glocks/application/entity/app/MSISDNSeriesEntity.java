package com.glocks.application.entity.app;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.glocks.application.features.trc.model.AuditTrailModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "operator_series")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MSISDNSeriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifiedOn;


    @Column(name = "series_start")
    private Integer seriesStart;


    @Column(name = "series_end")
    private Integer seriesEnd;


    @Column(name = "series_type")
    private String seriesType;

    @Column(name = "operator_name")
    private String operatorName;

    @Column(name = "remark")
    private String remarks;

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "length")
    private String length;

    @Transient
    private AuditTrailModel auditTrailModel;

    public Long getId() {
        return id;
    }

    public MSISDNSeriesEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public MSISDNSeriesEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public MSISDNSeriesEntity setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public Integer getSeriesStart() {
        return seriesStart;
    }

    public MSISDNSeriesEntity setSeriesStart(Integer seriesStart) {
        this.seriesStart = seriesStart;
        return this;
    }

    public Integer getSeriesEnd() {
        return seriesEnd;
    }

    public MSISDNSeriesEntity setSeriesEnd(Integer seriesEnd) {
        this.seriesEnd = seriesEnd;
        return this;
    }

    public String getSeriesType() {
        return seriesType;
    }

    public MSISDNSeriesEntity setSeriesType(String seriesType) {
        this.seriesType = seriesType;
        return this;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public MSISDNSeriesEntity setOperatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public MSISDNSeriesEntity setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public MSISDNSeriesEntity setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getLength() {
        return length;
    }

    public MSISDNSeriesEntity setLength(String length) {
        this.length = length;
        return this;
    }

    public AuditTrailModel getAuditTrailModel() {
        return auditTrailModel;
    }

    public MSISDNSeriesEntity setAuditTrailModel(AuditTrailModel auditTrailModel) {
        this.auditTrailModel = auditTrailModel;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MSISDNSeriesEntity{");
        sb.append("id=").append(id);
        sb.append(", createdOn=").append(createdOn);
        sb.append(", modifiedOn=").append(modifiedOn);
        sb.append(", seriesStart=").append(seriesStart);
        sb.append(", seriesEnd=").append(seriesEnd);
        sb.append(", seriesType='").append(seriesType).append('\'');
        sb.append(", operatorName='").append(operatorName).append('\'');
        sb.append(", remarks='").append(remarks).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", length='").append(length).append('\'');
        sb.append(", auditTrailModel=").append(auditTrailModel);
        sb.append('}');
        return sb.toString();
    }
}
