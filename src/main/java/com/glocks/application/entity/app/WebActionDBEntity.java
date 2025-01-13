package com.glocks.application.entity.app;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "web_action_db")
public class WebActionDBEntity implements Serializable {
    private static final long serialVersionUID = 1532730718708616149L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(hidden = true)
    @CreationTimestamp
    @Column(name = "created_on",columnDefinition = "DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date createdOn;
    @Schema(hidden = true)
    @UpdateTimestamp
    @Column(name = "modified_on",columnDefinition = "DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date modifiedOn;
    @Column(name = "data")
    private String data;

    @Column(name = "feature")
    private String feature;
    @Column(name = "state")
    private Integer state;
    @Column(name = "sub_feature")
    private String subFeature;
    @Column(name = "txn_id")
    private String txnId;
    @Column(name = "retry_count")
    private Integer retryCount;

    public Long getId() {
        return id;
    }

    public WebActionDBEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public WebActionDBEntity setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public WebActionDBEntity setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public String getData() {
        return data;
    }

    public WebActionDBEntity setData(String data) {
        this.data = data;
        return this;
    }

    public String getFeature() {
        return feature;
    }

    public WebActionDBEntity setFeature(String feature) {
        this.feature = feature;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public WebActionDBEntity setState(Integer state) {
        this.state = state;
        return this;
    }

    public String getSubFeature() {
        return subFeature;
    }

    public WebActionDBEntity setSubFeature(String subFeature) {
        this.subFeature = subFeature;
        return this;
    }

    public String getTxnId() {
        return txnId;
    }

    public WebActionDBEntity setTxnId(String txnId) {
        this.txnId = txnId;
        return this;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public WebActionDBEntity setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WebActionDBEntity{");
        sb.append("id=").append(id);
        sb.append(", createdOn=").append(createdOn);
        sb.append(", modifiedOn=").append(modifiedOn);
        sb.append(", data='").append(data).append('\'');
        sb.append(", feature='").append(feature).append('\'');
        sb.append(", state=").append(state);
        sb.append(", subFeature='").append(subFeature).append('\'');
        sb.append(", txnId='").append(txnId).append('\'');
        sb.append(", retryCount=").append(retryCount);
        sb.append('}');
        return sb.toString();
    }
}
