package app.apiservice.features.trc.csv_file_model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.time.LocalDateTime;

public class ApproveDeviceTACFileModel {
    @CsvBindByName(column = "Model Name")
    @CsvBindByPosition(position = 0)
    private String modelName;
    @CsvBindByName(column = "Manufacturer")
    @CsvBindByPosition(position = 1)
    private String manufacturer;
    @CsvBindByName(column = "Manufacturing Country")
    @CsvBindByPosition(position = 2)
    private String manufacturingLocation;

    @CsvBindByName(column = "OS")
    @CsvBindByPosition(position = 3)
    private String os;

    @CsvBindByName(column = "Launch Date")
    @CsvBindByPosition(position = 4)
    @CsvDate(value = "dd/MM/yyyy HH:mm")
    private LocalDateTime launchDate;
    @CsvBindByName(column = "Device Type")
    @CsvBindByPosition(position = 5)
    private String deviceType;
    @CsvBindByName(column = "Sim Slot")
    @CsvBindByPosition(position = 6)
    private Integer simSlot;
    @CsvBindByName(column = "TAC")
    @CsvBindByPosition(position = 7)
    private String tac;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturingLocation() {
        return manufacturingLocation;
    }

    public void setManufacturingLocation(String manufacturingLocation) {
        this.manufacturingLocation = manufacturingLocation;
    }

    public LocalDateTime getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDateTime launchDate) {
        this.launchDate = launchDate;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getSimSlot() {
        return simSlot;
    }

    public void setSimSlot(Integer simSlot) {
        this.simSlot = simSlot;
    }

    public String getTac() {
        return tac;
    }

    public void setTac(String tac) {
        this.tac = tac;
    }
}
