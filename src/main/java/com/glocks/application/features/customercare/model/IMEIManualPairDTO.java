package com.glocks.application.features.customercare.model;

import java.time.LocalDateTime;

public class IMEIManualPairDTO {

    private Long id;

    private LocalDateTime createdOn;

    private String imei1;

    private String msisdn1;

    private String requestId;

    private String requestType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getImei1() {
        return imei1;
    }

    public void setImei1(String imei1) {
        this.imei1 = imei1;
    }

    public String getMsisdn1() {
        return msisdn1;
    }

    public void setMsisdn1(String msisdn1) {
        this.msisdn1 = msisdn1;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IMEIManualPairDTO{");
        sb.append("id=").append(id);
        sb.append(", createdOn=").append(createdOn);
        sb.append(", imei1='").append(imei1).append('\'');
        sb.append(", msisdn1='").append(msisdn1).append('\'');
        sb.append(", requestId='").append(requestId).append('\'');
        sb.append(", requestType='").append(requestType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}