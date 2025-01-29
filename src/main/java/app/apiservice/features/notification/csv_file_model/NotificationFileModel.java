package app.apiservice.features.notification.csv_file_model;

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

    @CsvBindByName(column = "MSISDN")
    @CsvBindByPosition(position = 4)
    private String msisdn;

    @CsvBindByName(column = "Email")
    @CsvBindByPosition(position = 5)
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
        sb.append(", msisdn='").append(msisdn).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
