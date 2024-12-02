package com.glocks.application.features.notification.csv_file_model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class NotificationFileModel {
    @CsvBindByName(column = "Creation Date")
    @CsvBindByPosition(position = 0)
    private String creationDate;

    @CsvBindByName(column = "Channel Type")
    @CsvBindByPosition(position = 1)
    private String channelType;

    @CsvBindByName(column = "Feature Name")
    @CsvBindByPosition(position = 2)
    private String featureName;

    @CsvBindByName(column = "Feature Txn ID")
    @CsvBindByPosition(position = 3)
    private String featureTxnId;

    @CsvBindByName(column = "Language")
    @CsvBindByPosition(position = 4)
    private String language;

    @CsvBindByName(column = "Subject")
    @CsvBindByPosition(position = 5)
    private String subject;

    @CsvBindByName(column = "Status")
    @CsvBindByPosition(position = 6)
    private String status;

    @CsvBindByName(column = "Notification Sent Time")
    @CsvBindByPosition(position = 7)
    private String notificationSentTime;

    @CsvBindByName(column = "MSISDN")
    @CsvBindByPosition(position = 8)
    private String msisdn;

    @CsvBindByName(column = "Email")
    @CsvBindByPosition(position = 9)
    private String email;

  /*  @CsvBindByName(column = "Subject")
    @CsvBindByPosition(position = 10)
    private String subject;*/

    public String getCreationDate() {
        return creationDate;
    }

    public NotificationFileModel setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getChannelType() {
        return channelType;
    }

    public NotificationFileModel setChannelType(String channelType) {
        this.channelType = channelType;
        return this;
    }

    public String getFeatureName() {
        return featureName;
    }

    public NotificationFileModel setFeatureName(String featureName) {
        this.featureName = featureName;
        return this;
    }

    public String getFeatureTxnId() {
        return featureTxnId;
    }

    public NotificationFileModel setFeatureTxnId(String featureTxnId) {
        this.featureTxnId = featureTxnId;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public NotificationFileModel setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public NotificationFileModel setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public NotificationFileModel setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getNotificationSentTime() {
        return notificationSentTime;
    }

    public NotificationFileModel setNotificationSentTime(String notificationSentTime) {
        this.notificationSentTime = notificationSentTime;
        return this;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public NotificationFileModel setMsisdn(String msisdn) {
        this.msisdn = msisdn;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public NotificationFileModel setEmail(String email) {
        this.email = email;
        return this;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NotificationFileModel{");
        sb.append("creationDate='").append(creationDate).append('\'');
        sb.append(", channelType='").append(channelType).append('\'');
        sb.append(", featureName='").append(featureName).append('\'');
        sb.append(", featureTxnId='").append(featureTxnId).append('\'');
        sb.append(", language='").append(language).append('\'');
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", notificationSentTime='").append(notificationSentTime).append('\'');
        sb.append(", msisdn='").append(msisdn).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
