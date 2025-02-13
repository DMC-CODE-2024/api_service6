package app.apiservice.features.trc.csv_file_model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.time.LocalDateTime;

public class LMDataFileModel {
    @CsvBindByName(column = "Uploaded Date")
    @CsvBindByPosition(position = 0)
    @CsvDate(value = "dd/MM/yyyy HH:mm")
    private LocalDateTime uploadedDate;
    @CsvBindByName(column = "Serial Number")
    @CsvBindByPosition(position = 1)
    private String serialNumber;
    @CsvBindByName(column = "IMEI")
    @CsvBindByPosition(position = 2)
    private String imei;

    @CsvBindByName(column = "Manufacturer ID")
    @CsvBindByPosition(position = 3)
    private String manufactureID;
    @CsvBindByName(column = "Manufacturer Name")
    @CsvBindByPosition(position = 4)
    private String manufactureName;

    public LocalDateTime getUploadedDate() {
        return uploadedDate;
    }

    public LMDataFileModel setUploadedDate(LocalDateTime uploadedDate) {
        this.uploadedDate = uploadedDate;
        return this;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public LMDataFileModel setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public String getImei() {
        return imei;
    }

    public LMDataFileModel setImei(String imei) {
        this.imei = imei;
        return this;
    }

    public String getManufactureID() {
        return manufactureID;
    }

    public LMDataFileModel setManufactureID(String manufactureID) {
        this.manufactureID = manufactureID;
        return this;
    }

    public String getManufactureName() {
        return manufactureName;
    }

    public LMDataFileModel setManufactureName(String manufactureName) {
        this.manufactureName = manufactureName;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LMDataFileModel{");
        sb.append("uploadedDate='").append(uploadedDate).append('\'');
        sb.append(", serialNumber='").append(serialNumber).append('\'');
        sb.append(", imei='").append(imei).append('\'');
        sb.append(", manufactureID='").append(manufactureID).append('\'');
        sb.append(", manufactureName='").append(manufactureName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
