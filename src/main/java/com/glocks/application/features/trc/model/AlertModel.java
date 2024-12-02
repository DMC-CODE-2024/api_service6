package com.glocks.application.features.trc.model;

import java.util.Objects;

public class AlertModel {
    private String alertId;
    private String alertMessage;
    private String alertProcess;
    private String description;
    private String featureName;
    private String ip;
    private String priority;
    private String remarks;
    private String serverName;
    private int status;
    private int userId;
    private String txnId;


    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public String getAlertProcess() {
        return alertProcess;
    }

    public void setAlertProcess(String alertProcess) {
        this.alertProcess = alertProcess;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AlertModel that = (AlertModel) object;
        return status == that.status && userId == that.userId && Objects.equals(alertId, that.alertId) && Objects.equals(alertMessage, that.alertMessage) && Objects.equals(alertProcess, that.alertProcess) && Objects.equals(description, that.description) && Objects.equals(featureName, that.featureName) && Objects.equals(ip, that.ip) && Objects.equals(priority, that.priority) && Objects.equals(remarks, that.remarks) && Objects.equals(serverName, that.serverName) && Objects.equals(txnId, that.txnId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alertId, alertMessage, alertProcess, description, featureName, ip, priority, remarks, serverName, status, userId, txnId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlertModel{");
        sb.append("alertId='").append(alertId).append('\'');
        sb.append(", alertMessage='").append(alertMessage).append('\'');
        sb.append(", alertProcess='").append(alertProcess).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", featureName='").append(featureName).append('\'');
        sb.append(", ip='").append(ip).append('\'');
        sb.append(", priority='").append(priority).append('\'');
        sb.append(", remarks='").append(remarks).append('\'');
        sb.append(", serverName='").append(serverName).append('\'');
        sb.append(", status=").append(status);
        sb.append(", userId=").append(userId);
        sb.append(", txnId='").append(txnId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
