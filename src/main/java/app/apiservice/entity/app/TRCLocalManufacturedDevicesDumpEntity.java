package app.apiservice.entity.app;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import app.apiservice.features.trc.model.AuditTrailModel;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "trc_local_manufactured_device_data")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TRCLocalManufacturedDevicesDumpEntity implements Serializable {
    private static final long serialVersionUID = -6576829255806294196L;
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
    @Column(name = "imei")
    private String imei;
    @Column(name = "actual_imei")
    private String actualImei;
    @Column(name = "tac")
    private String tac;
    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "manufacturer_id")
    private String manufacturerId;
    @Column(name = "manufacturer_name")
    private String manufacturerName;
    @Column(name = "manufacturing_date")
    private String manufactureringDate;
    @Transient
  //  @JsonProperty(value = "auditTrailModel", access = JsonProperty.Access.WRITE_ONLY)
    private AuditTrailModel auditTrailModel;
    public String getTac() {
        return tac;
    }

    public void setTac(String tac) {
        this.tac = tac;
    }
    public String getActualImei() {
        return actualImei;
    }

    public TRCLocalManufacturedDevicesDumpEntity setActualImei(String actualImei) {
        this.actualImei = actualImei;
        return this;
    }

    public AuditTrailModel getAuditTrailModel() {
        return auditTrailModel;
    }

    public TRCLocalManufacturedDevicesDumpEntity setAuditTrailModel(AuditTrailModel auditTrailModel) {
        this.auditTrailModel = auditTrailModel;
        return this;
    }

    public Long getId() {
        return id;
    }

    public TRCLocalManufacturedDevicesDumpEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public TRCLocalManufacturedDevicesDumpEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public TRCLocalManufacturedDevicesDumpEntity setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public String getImei() {
        return imei;
    }

    public TRCLocalManufacturedDevicesDumpEntity setImei(String imei) {
        this.imei = imei;
        return this;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public TRCLocalManufacturedDevicesDumpEntity setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public String getManufactureringDate() {
        return manufactureringDate;
    }

    public TRCLocalManufacturedDevicesDumpEntity setManufactureringDate(String manufactureringDate) {
        this.manufactureringDate = manufactureringDate;
        return this;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public TRCLocalManufacturedDevicesDumpEntity setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
        return this;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public TRCLocalManufacturedDevicesDumpEntity setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
        return this;
    }

    @Override
    public String toString() {
        return "TRCLocalManufacturedDevicesDumpEntity{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                ", modifiedOn=" + modifiedOn +
                ", imei='" + imei + '\'' +
                ", actualImei='" + actualImei + '\'' +
                ", tac='" + tac + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", manufacturerId='" + manufacturerId + '\'' +
                ", manufacturerName='" + manufacturerName + '\'' +
                ", manufactureringDate='" + manufactureringDate + '\'' +
                ", auditTrailModel=" + auditTrailModel +
                '}';
    }
}
