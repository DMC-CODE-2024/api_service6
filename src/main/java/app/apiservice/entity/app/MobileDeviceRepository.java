package app.apiservice.entity.app;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import app.apiservice.features.trc.model.AuditTrailModel;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mobile_device_repository")
public class MobileDeviceRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Schema(hidden = true)
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "created_on", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdOn;
    @Schema(hidden = true)
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "modified_on", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime modifiedOn;
    @Column(name = "launch_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime launchDate;
    @Column(name = "model_name")
    private String modelName;
    private String manufacturer;
    @Column(name = "manufacturing_location")
    private String manufacturingLocation;
    @Column(name = "os")
    private String os;
    @Column(name = "device_type")
    private String deviceType;
    @Column(name = "device_id")
    private String deviceId;
    @Column(name ="trc_type_approved_by")
    private String approvedBy;
    @Column(name = "trc_approved_status")
    private String trcApprovedStatus;
    @Column(name = "sim_slot", length = 1, columnDefinition = "int DEFAULT '0'")
    private Integer simSlot;
    @Transient
    private String action;
    @Column(name = "trc_approval_date")
    private LocalDateTime trcApprovalDate;
    @Column(name = "remark")
    private String remark = "";
    @Column(name = "is_type_approved")
    private int isTypeApproved;

    @Transient
    @JsonProperty(value = "auditTrailModel", access = JsonProperty.Access.WRITE_ONLY)
    private AuditTrailModel auditTrailModel;

    public int getIsTypeApproved() {
        return isTypeApproved;
    }

    public void setIsTypeApproved(int isTypeApproved) {
        this.isTypeApproved = isTypeApproved;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getTrcApprovalDate() {
        return trcApprovalDate;
    }

    public void setTrcApprovalDate(LocalDateTime trcApprovalDate) {
        this.trcApprovalDate = trcApprovalDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public AuditTrailModel getAuditTrailModel() {
        return auditTrailModel;
    }

    public void setAuditTrailModel(AuditTrailModel auditTrailModel) {
        this.auditTrailModel = auditTrailModel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public LocalDateTime getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDateTime launchDate) {
        this.launchDate = launchDate;
    }

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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTrcApprovedStatus() {
        return trcApprovedStatus;
    }

    public void setTrcApprovedStatus(String trcApprovedStatus) {
        this.trcApprovedStatus = trcApprovedStatus;
    }

    public Integer getSimSlot() {
        return simSlot;
    }

    public void setSimSlot(Integer simSlot) {
        this.simSlot = simSlot;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MobileDeviceRepository{");
        sb.append("id=").append(id);
        sb.append(", createdOn=").append(createdOn);
        sb.append(", modifiedOn=").append(modifiedOn);
        sb.append(", launchDate=").append(launchDate);
        sb.append(", modelName='").append(modelName).append('\'');
        sb.append(", manufacturer='").append(manufacturer).append('\'');
        sb.append(", manufacturingLocation='").append(manufacturingLocation).append('\'');
        sb.append(", os='").append(os).append('\'');
        sb.append(", deviceType='").append(deviceType).append('\'');
        sb.append(", deviceId='").append(deviceId).append('\'');
        sb.append(", approvedBy='").append(approvedBy).append('\'');
        sb.append(", trcApprovedStatus='").append(trcApprovedStatus).append('\'');
        sb.append(", simSlot=").append(simSlot);
        sb.append(", action='").append(action).append('\'');
        sb.append(", trcApprovalDate=").append(trcApprovalDate);
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", isTypeApproved=").append(isTypeApproved);
        sb.append(", auditTrailModel=").append(auditTrailModel);
        sb.append('}');
        return sb.toString();
    }



       /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "allocation_date", columnDefinition = "timestamp DEFAULT NULL")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime allocationDate;

    @Column(name = "announce_date", columnDefinition = "timestamp DEFAULT NULL")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime announceDate;

    @Column(name = "band_detail", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String bandDetail = "";

    @Column(name = "battery_capacity", length = 5, columnDefinition = "int DEFAULT '0'")
    private Integer batteryCapacity = 0;

    @Column(name = "battery_charging", length = 100, columnDefinition = "varchar(100) DEFAULT ''")
    private String batteryCharging = "";

    @Column(name = "battery_type", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String batteryType = "";

    @Column(name = "body_dimension", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String bodyDimension = "";

    @Column(name = "body_weight", length = 20, columnDefinition = "varchar(20) DEFAULT ''")
    private String bodyWeight = "";

    @Column(name = "brand_name", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String brandName = "";

    @Column(name = "comms_bluetooth", length = 100, columnDefinition = "varchar(100) DEFAULT ''")
    private String commsBluetooth = "";

    @Column(name = "comms_gps", length = 100, columnDefinition = "varchar(100) DEFAULT ''")
    private String commsGPS = "";

    @Column(name = "comms_nfc", length = 1, columnDefinition = "int DEFAULT '0'")
    private Integer commsNFC = 0;

    @Column(name = "comms_radio", length = 1, columnDefinition = "int DEFAULT '0'")
    private Integer commsRadio = 0;

    @Column(name = "comms_usb", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String commsUSB = "";

    @Column(name = "comms_wlan", length = 100, columnDefinition = "varchar(100) DEFAULT ''")
    private String commsWLAN = "";

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(name = "created_on", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(name = "modified_on", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime modifiedOn;

    @Column(name = "device_id", length = 8, columnDefinition = "varchar(8) DEFAULT '0'", unique = true)
    private String deviceId;

    @Column(name = "marketing_name", length = 100, columnDefinition = "varchar(100) DEFAULT ''")
    private String marketingName = "";

    @Column(length = 100, columnDefinition = "varchar(100) DEFAULT ''")
    private String manufacturer;

    @Column(name = "manufacturing_location", length = 100, columnDefinition = "varchar(100) DEFAULT ''")
    private String manufacturingLocation;

    @Column(name = "model_name", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String modelName;

    @Column(name = "oem", length = 100, columnDefinition = "varchar(100) DEFAULT ''")
    private String oem = "";

    @Column(name = "organization_id", length = 25, columnDefinition = "varchar(25) DEFAULT ''")
    private String organizationId = "";

    @Column(name = "device_type", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String deviceType;

    @Column(name = "imei_quantity", length = 1, columnDefinition = "int DEFAULT '0'")
    private Integer imeiQuantity = 0;

    @Column(name = "sim_slot", length = 1, columnDefinition = "int DEFAULT '0'")
    private Integer simSlot;

    @Column(name = "esim_support", length = 1, columnDefinition = "int DEFAULT '0'")
    private Integer esimSupport = 0; // Configuration table entry 'Yes', 'No'

    @Column(name = "softsim_support", length = 1, columnDefinition = "int DEFAULT '0'")
    private Integer softsimSupport = 0; // Configuration table entry 'Yes', 'No'

    @Column(name = "sim_type", length = 15, columnDefinition = "varchar(15) DEFAULT ''")
    private String simType = "";

    @Column(name = "os", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String os;

    @Column(name = "os_base_version", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String osBaseVersion = "";

  *//*  @Column(name = "launch_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime launchDate;
*//*
  @Column(name = "launch_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDateTime launchDate;
    @Column(name = "device_status", length = 20, columnDefinition = "varchar(20) DEFAULT ''")
    private String deviceStatus = "";

    @Column(name = "discontinue_date", columnDefinition = "timestamp DEFAULT NULL")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime discontinueDate;

    @Column(name = "network_technology_gsm", length = 1, columnDefinition = "int DEFAULT '0'") //2G
    private Integer networkTechnologyGSM = 0; // Configuration table entry 'Yes', 'No'

    @Column(name = "network_technology_cdma", length = 1, columnDefinition = "int DEFAULT '0'")
    private Integer networkTechnologyCDMA = 0; // Configuration table entry 'Yes', 'No'

    @Column(name = "network_technology_evdo", length = 1, columnDefinition = "int DEFAULT '0'")
    private Integer networkTechnologyEVDO = 0; // Configuration table entry 'Yes', 'No'

    @Column(name = "network_technology_lte", length = 1, columnDefinition = "int DEFAULT '0'")
    private Integer networkTechnologyLTE = 0; // Configuration table entry 'Yes', 'No'

    @Column(name = "network_technology_5g", length = 1, columnDefinition = "int DEFAULT '0'")
    private Integer networkTechnology5G = 0; // Configuration table entry 'Yes', 'No'

    @Column(name = "network_technology_6g", length = 1, columnDefinition = "int DEFAULT '0'")
    private Integer networkTechnology6G = 0; // Configuration table entry 'Yes', 'No'

    @Column(name = "network_technology_7g", length = 1, columnDefinition = "int DEFAULT '0'")
    private Integer networkTechnology7G = 0; // Configuration table entry 'Yes', 'No'

    @Column(name = "network_2g_band", length = 100, columnDefinition = "varchar(100) DEFAULT ''")
    private String network2GBand = "";

    @Column(name = "network_3g_band", length = 100, columnDefinition = "varchar(100) DEFAULT ''")
    private String network3GBand = "";

    @Column(name = "network_4g_band", length = 100, columnDefinition = "varchar(100) DEFAULT ''")
    private String network4GBand = "";

    @Column(name = "network_5g_band", length = 100, columnDefinition = "varchar(100) DEFAULT ''")
    private String network5GBand = "";

    @Column(name = "network_6g_band", length = 100, columnDefinition = "varchar(100) DEFAULT ''")
    private String network6GBand = "";

    @Column(name = "network_7g_band", length = 100, columnDefinition = "varchar(100) DEFAULT ''")
    private String network7GBand = "";

    @Column(name = "network_speed", length = 100, columnDefinition = "varchar(100) DEFAULT ''")
    private String networkSpeed = "";

    @Column(name = "display_type", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String displayType = "";

    @Column(name = "display_size", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String displaySize = "";

    @Column(name = "display_resolution", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String displayResolution = "";

    @Column(name = "display_protection", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String displayProtection = "";

    @Column(name = "platform_chipset", length = 100, columnDefinition = "varchar(100) DEFAULT ''")
    private String platformChipset = "";

    @Column(name = "platform_cpu", length = 100, columnDefinition = "varchar(100) DEFAULT ''")
    private String platformCPU = "";

    @Column(name = "platform_gpu", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String platformGPU = "";

    @Column(name = "memory_card_slot", length = 1, columnDefinition = "int DEFAULT '0'")
    private Integer memoryCardSlot = 0; // Changed from String to integer. Configuration table entry 'Yes', 'No'

    @Column(name = "memory_internal", length = 5, columnDefinition = "int DEFAULT '0'")
    private Integer memoryInternal = 0;

    @Column(name = "ram", length = 20, columnDefinition = "varchar(20) DEFAULT ''")
    private String ram = "0";

    @Column(name = "main_camera_type", length = 1, columnDefinition = "int DEFAULT '0'")
    private Integer mainCameraType = 0; // Changed from String to integer. Configuration table entry 'Single Camera', 'Dual Camera', 'Triple Camera'

    @Column(name = "main_camera_spec", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String mainCameraSpec = ""; // Changed from int to varchar as per example

    @Column(name = "main_camera_feature", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String mainCameraFeature = ""; // Changed from int to varchar as per example

    @Column(name = "main_camera_video", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String mainCameraVideo = "";

    @Column(name = "selfie_camera_type", length = 1, columnDefinition = "int DEFAULT '0'")
    private Integer selfieCameraType = 0; // Changed from String to integer. Configuration table entry 'Single Camera', 'Dual Camera', 'Triple Camera'

    @Column(name = "selfie_camera_spec", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String selfieCameraSpec = ""; // Changed from int to varchar as per example

    @Column(name = "selfie_camera_feature", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String selfieCameraFeature = ""; // Changed from int to varchar as per example

    @Column(name = "selfie_camera_video", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String selfieCameraVideo = "";

    @Column(name = "sound_loudspeaker", length = 1, columnDefinition = "int DEFAULT '0'")
    private Integer soundLoudspeaker = 0; // Configuration table entry 'Yes', 'No'

    @Column(name = "sound_3_5mm_jack", length = 1, columnDefinition = "int DEFAULT '0'")
    private Integer sound35mmJack = 0; // Configuration table entry 'Yes', 'No'

    @Column(name = "sensor", length = 50, columnDefinition = "varchar(50) DEFAULT ''")
    private String sensor = "";

    @Column(name = "removable_uicc", length = 2, columnDefinition = "int DEFAULT '0'")
    private Integer removableUICC = 0;

    @Column(name = "removable_euicc", length = 2, columnDefinition = "int DEFAULT '0'")
    private Integer removableEUICC = 0;

    @Column(name = "nonremovable_uicc", length = 2, columnDefinition = "int DEFAULT '0'")
    private Integer nonremovableUICC = 0;

    @Column(name = "nonremovable_euicc", length = 2, columnDefinition = "int DEFAULT '0'")
    private Integer nonremovableEUICC = 0;

    @Column(name = "launch_price_asian_market", columnDefinition = "double DEFAULT '0'")
    private Double launchPriceAsianMarket = 0.0;

    @Column(name = "launch_price_us_market", columnDefinition = "double DEFAULT '0'")
    private Double launchPriceUSMarket = 0.0;

    @Column(name = "launch_price_europe_market", columnDefinition = "double DEFAULT '0'")
    private Double launchPriceEuropeMarket = 0.0;

    @Column(name = "launch_price_international_market", columnDefinition = "double DEFAULT '0'")
    private Double launchPriceInternationalMarket = 0.0;

    @Column(name = "launch_price_cambodia_market", columnDefinition = "double DEFAULT '0'")
    private Double launchPriceCambodiaMarket = 0.0;

    @Column(name = "custom_price_of_device", columnDefinition = "double DEFAULT '0'")
    private Double customPriceOfDevice = 0.0;

    @Column(name = "source_of_cambodia_market_price", length = 100, columnDefinition = "varchar(100) DEFAULT ''")
    private String sourceOfCambodiaMarketPrice = "";

    @Column(name = "reported_global_issue", length = 255, columnDefinition = "varchar(255) DEFAULT ''")
    // Need to inform about field length change
    private String reportedGlobalIssue = "";

    @Column(name = "reported_local_issue", length = 255, columnDefinition = "varchar(255) DEFAULT ''")
    // Need to inform about field length change
    private String reportedLocalIssue = "";

    @Column(name = "device_state", length = 3, columnDefinition = "int DEFAULT '0'")
    private Integer deviceState = 0;

    @Column(name = "user_id", columnDefinition = "int DEFAULT '0'")
    private Integer userId;
    @Transient
    @Column(name = "user_type", columnDefinition = "int DEFAULT '0'")
    private Integer userType = 0;

    @Column(name = "remark", length = 1000, columnDefinition = "varchar(1000) DEFAULT ''")
    private String remark = "";

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "mdr_id", insertable = false, updatable = false)
    private List<AttachedFileInfo> attachedFiles = new ArrayList<>();

    @Column(name = "trc_approved_status")
    private String trcApprovedStatus;

    @Column(name ="trc_type_approved_by")
    private String approvedBy;

    @Column(name = "color", length = 100, columnDefinition = "varchar(100) DEFAULT ''")
    private String color = "";

    @Column(name = "trc_approval_date")
    private LocalDateTime trcApprovalDate;


    @Column(name = "is_test_imei")
    private Integer isTestImei;


    @Column(name = "os_current_version")
    private String osCurrentVersion;

    @Column(name = "network_specific_identifier")
    private Integer networkSpecificIdentifier;

    @Column(name = "is_type_approved")
    private int isTypeApproved;


    //is it required
    @Transient
    //@Column(name = "trc_approval_status")
    private String trcApprovalStatus;

    @Transient
    private String action;
    @Transient
    @JsonProperty(value = "auditTrailModel", access = JsonProperty.Access.WRITE_ONLY)
    private AuditTrailModel auditTrailModel;
*/
}

