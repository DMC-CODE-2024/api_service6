package com.glocks.application.entity.app;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.glocks.application.features.trc.model.AuditTrailModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mobile_device_repository")
public class MobileDeviceRepository {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "allocation_date", columnDefinition = "timestamp DEFAULT NULL")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime allocationDate;

    @Column(name = "announce_date", columnDefinition = "timestamp DEFAULT NULL")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "created_on", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
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

  /*  @Column(name = "launch_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime launchDate;
*/
  @Column(name = "launch_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDateTime launchDate;
    @Column(name = "device_status", length = 20, columnDefinition = "varchar(20) DEFAULT ''")
    private String deviceStatus = "";

    @Column(name = "discontinue_date", columnDefinition = "timestamp DEFAULT NULL")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    public int getIsTypeApproved() {
        return isTypeApproved;
    }

    public void setIsTypeApproved(int isTypeApproved) {
        this.isTypeApproved = isTypeApproved;
    }

    public String getAction() {
        return action;
    }

    public MobileDeviceRepository setAction(String action) {
        this.action = action;
        return this;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public MobileDeviceRepository setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
        return this;
    }

    public String getColor() {
        return color;
    }

    public MobileDeviceRepository setColor(String color) {
        this.color = color;
        return this;
    }

    public LocalDateTime getTrcApprovalDate() {
        return trcApprovalDate;
    }

    public void setTrcApprovalDate(LocalDateTime trcApprovalDate) {
        this.trcApprovalDate = trcApprovalDate;
    }

    public Integer getIsTestImei() {
        return isTestImei;
    }

    public MobileDeviceRepository setIsTestImei(Integer isTestImei) {
        this.isTestImei = isTestImei;
        return this;
    }

    public String getOsCurrentVersion() {
        return osCurrentVersion;
    }

    public MobileDeviceRepository setOsCurrentVersion(String osCurrentVersion) {
        this.osCurrentVersion = osCurrentVersion;
        return this;
    }

    public Integer getNetworkSpecificIdentifier() {
        return networkSpecificIdentifier;
    }

    public MobileDeviceRepository setNetworkSpecificIdentifier(Integer networkSpecificIdentifier) {
        this.networkSpecificIdentifier = networkSpecificIdentifier;
        return this;
    }


    public Integer getId() {
        return id;
    }

    public MobileDeviceRepository setId(Integer id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getAllocationDate() {
        return allocationDate;
    }

    public MobileDeviceRepository setAllocationDate(LocalDateTime allocationDate) {
        this.allocationDate = allocationDate;
        return this;
    }

    public LocalDateTime getAnnounceDate() {
        return announceDate;
    }

    public MobileDeviceRepository setAnnounceDate(LocalDateTime announceDate) {
        this.announceDate = announceDate;
        return this;
    }

    public String getBandDetail() {
        return bandDetail;
    }

    public MobileDeviceRepository setBandDetail(String bandDetail) {
        this.bandDetail = bandDetail;
        return this;
    }

    public Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    public MobileDeviceRepository setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
        return this;
    }

    public String getBatteryCharging() {
        return batteryCharging;
    }

    public MobileDeviceRepository setBatteryCharging(String batteryCharging) {
        this.batteryCharging = batteryCharging;
        return this;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public MobileDeviceRepository setBatteryType(String batteryType) {
        this.batteryType = batteryType;
        return this;
    }

    public String getBodyDimension() {
        return bodyDimension;
    }

    public MobileDeviceRepository setBodyDimension(String bodyDimension) {
        this.bodyDimension = bodyDimension;
        return this;
    }

    public String getBodyWeight() {
        return bodyWeight;
    }

    public MobileDeviceRepository setBodyWeight(String bodyWeight) {
        this.bodyWeight = bodyWeight;
        return this;
    }

    public String getBrandName() {
        return brandName;
    }

    public MobileDeviceRepository setBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public String getCommsBluetooth() {
        return commsBluetooth;
    }

    public MobileDeviceRepository setCommsBluetooth(String commsBluetooth) {
        this.commsBluetooth = commsBluetooth;
        return this;
    }

    public String getCommsGPS() {
        return commsGPS;
    }

    public MobileDeviceRepository setCommsGPS(String commsGPS) {
        this.commsGPS = commsGPS;
        return this;
    }

    public Integer getCommsNFC() {
        return commsNFC;
    }

    public MobileDeviceRepository setCommsNFC(Integer commsNFC) {
        this.commsNFC = commsNFC;
        return this;
    }

    public Integer getCommsRadio() {
        return commsRadio;
    }

    public MobileDeviceRepository setCommsRadio(Integer commsRadio) {
        this.commsRadio = commsRadio;
        return this;
    }

    public String getCommsUSB() {
        return commsUSB;
    }

    public MobileDeviceRepository setCommsUSB(String commsUSB) {
        this.commsUSB = commsUSB;
        return this;
    }

    public String getCommsWLAN() {
        return commsWLAN;
    }

    public MobileDeviceRepository setCommsWLAN(String commsWLAN) {
        this.commsWLAN = commsWLAN;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public MobileDeviceRepository setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public MobileDeviceRepository setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public MobileDeviceRepository setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public MobileDeviceRepository setMarketingName(String marketingName) {
        this.marketingName = marketingName;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public MobileDeviceRepository setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getManufacturingLocation() {
        return manufacturingLocation;
    }

    public MobileDeviceRepository setManufacturingLocation(String manufacturingLocation) {
        this.manufacturingLocation = manufacturingLocation;
        return this;
    }

    public String getModelName() {
        return modelName;
    }

    public MobileDeviceRepository setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getOem() {
        return oem;
    }

    public MobileDeviceRepository setOem(String oem) {
        this.oem = oem;
        return this;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public MobileDeviceRepository setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public MobileDeviceRepository setDeviceType(String deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public Integer getImeiQuantity() {
        return imeiQuantity;
    }

    public MobileDeviceRepository setImeiQuantity(Integer imeiQuantity) {
        this.imeiQuantity = imeiQuantity;
        return this;
    }

    public Integer getSimSlot() {
        return simSlot;
    }

    public MobileDeviceRepository setSimSlot(Integer simSlot) {
        this.simSlot = simSlot;
        return this;
    }

    public Integer getEsimSupport() {
        return esimSupport;
    }

    public MobileDeviceRepository setEsimSupport(Integer esimSupport) {
        this.esimSupport = esimSupport;
        return this;
    }

    public Integer getSoftsimSupport() {
        return softsimSupport;
    }

    public MobileDeviceRepository setSoftsimSupport(Integer softsimSupport) {
        this.softsimSupport = softsimSupport;
        return this;
    }

    public String getSimType() {
        return simType;
    }

    public MobileDeviceRepository setSimType(String simType) {
        this.simType = simType;
        return this;
    }

    public String getOs() {
        return os;
    }

    public MobileDeviceRepository setOs(String os) {
        this.os = os;
        return this;
    }

    public String getOsBaseVersion() {
        return osBaseVersion;
    }

    public MobileDeviceRepository setOsBaseVersion(String osBaseVersion) {
        this.osBaseVersion = osBaseVersion;
        return this;
    }

    public LocalDateTime getLaunchDate() {
        return launchDate;
    }

    public MobileDeviceRepository setLaunchDate(LocalDateTime launchDate) {
        this.launchDate = launchDate;
        return this;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public MobileDeviceRepository setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
        return this;
    }

    public LocalDateTime getDiscontinueDate() {
        return discontinueDate;
    }

    public MobileDeviceRepository setDiscontinueDate(LocalDateTime discontinueDate) {
        this.discontinueDate = discontinueDate;
        return this;
    }

    public Integer getNetworkTechnologyGSM() {
        return networkTechnologyGSM;
    }

    public MobileDeviceRepository setNetworkTechnologyGSM(Integer networkTechnologyGSM) {
        this.networkTechnologyGSM = networkTechnologyGSM;
        return this;
    }

    public Integer getNetworkTechnologyCDMA() {
        return networkTechnologyCDMA;
    }

    public MobileDeviceRepository setNetworkTechnologyCDMA(Integer networkTechnologyCDMA) {
        this.networkTechnologyCDMA = networkTechnologyCDMA;
        return this;
    }

    public Integer getNetworkTechnologyEVDO() {
        return networkTechnologyEVDO;
    }

    public MobileDeviceRepository setNetworkTechnologyEVDO(Integer networkTechnologyEVDO) {
        this.networkTechnologyEVDO = networkTechnologyEVDO;
        return this;
    }

    public Integer getNetworkTechnologyLTE() {
        return networkTechnologyLTE;
    }

    public MobileDeviceRepository setNetworkTechnologyLTE(Integer networkTechnologyLTE) {
        this.networkTechnologyLTE = networkTechnologyLTE;
        return this;
    }

    public Integer getNetworkTechnology5G() {
        return networkTechnology5G;
    }

    public MobileDeviceRepository setNetworkTechnology5G(Integer networkTechnology5G) {
        this.networkTechnology5G = networkTechnology5G;
        return this;
    }

    public Integer getNetworkTechnology6G() {
        return networkTechnology6G;
    }

    public MobileDeviceRepository setNetworkTechnology6G(Integer networkTechnology6G) {
        this.networkTechnology6G = networkTechnology6G;
        return this;
    }

    public Integer getNetworkTechnology7G() {
        return networkTechnology7G;
    }

    public MobileDeviceRepository setNetworkTechnology7G(Integer networkTechnology7G) {
        this.networkTechnology7G = networkTechnology7G;
        return this;
    }

    public String getNetwork2GBand() {
        return network2GBand;
    }

    public MobileDeviceRepository setNetwork2GBand(String network2GBand) {
        this.network2GBand = network2GBand;
        return this;
    }

    public String getNetwork3GBand() {
        return network3GBand;
    }

    public MobileDeviceRepository setNetwork3GBand(String network3GBand) {
        this.network3GBand = network3GBand;
        return this;
    }

    public String getNetwork4GBand() {
        return network4GBand;
    }

    public MobileDeviceRepository setNetwork4GBand(String network4GBand) {
        this.network4GBand = network4GBand;
        return this;
    }

    public String getNetwork5GBand() {
        return network5GBand;
    }

    public MobileDeviceRepository setNetwork5GBand(String network5GBand) {
        this.network5GBand = network5GBand;
        return this;
    }

    public String getNetwork6GBand() {
        return network6GBand;
    }

    public MobileDeviceRepository setNetwork6GBand(String network6GBand) {
        this.network6GBand = network6GBand;
        return this;
    }

    public String getNetwork7GBand() {
        return network7GBand;
    }

    public MobileDeviceRepository setNetwork7GBand(String network7GBand) {
        this.network7GBand = network7GBand;
        return this;
    }

    public String getNetworkSpeed() {
        return networkSpeed;
    }

    public MobileDeviceRepository setNetworkSpeed(String networkSpeed) {
        this.networkSpeed = networkSpeed;
        return this;
    }

    public String getDisplayType() {
        return displayType;
    }

    public MobileDeviceRepository setDisplayType(String displayType) {
        this.displayType = displayType;
        return this;
    }

    public String getDisplaySize() {
        return displaySize;
    }

    public MobileDeviceRepository setDisplaySize(String displaySize) {
        this.displaySize = displaySize;
        return this;
    }

    public String getDisplayResolution() {
        return displayResolution;
    }

    public MobileDeviceRepository setDisplayResolution(String displayResolution) {
        this.displayResolution = displayResolution;
        return this;
    }

    public String getDisplayProtection() {
        return displayProtection;
    }

    public MobileDeviceRepository setDisplayProtection(String displayProtection) {
        this.displayProtection = displayProtection;
        return this;
    }

    public String getPlatformChipset() {
        return platformChipset;
    }

    public MobileDeviceRepository setPlatformChipset(String platformChipset) {
        this.platformChipset = platformChipset;
        return this;
    }

    public String getPlatformCPU() {
        return platformCPU;
    }

    public MobileDeviceRepository setPlatformCPU(String platformCPU) {
        this.platformCPU = platformCPU;
        return this;
    }

    public String getPlatformGPU() {
        return platformGPU;
    }

    public MobileDeviceRepository setPlatformGPU(String platformGPU) {
        this.platformGPU = platformGPU;
        return this;
    }

    public Integer getMemoryCardSlot() {
        return memoryCardSlot;
    }

    public MobileDeviceRepository setMemoryCardSlot(Integer memoryCardSlot) {
        this.memoryCardSlot = memoryCardSlot;
        return this;
    }

    public Integer getMemoryInternal() {
        return memoryInternal;
    }

    public MobileDeviceRepository setMemoryInternal(Integer memoryInternal) {
        this.memoryInternal = memoryInternal;
        return this;
    }

    public String getRam() {
        return ram;
    }

    public MobileDeviceRepository setRam(String ram) {
        this.ram = ram;
        return this;
    }

    public Integer getMainCameraType() {
        return mainCameraType;
    }

    public MobileDeviceRepository setMainCameraType(Integer mainCameraType) {
        this.mainCameraType = mainCameraType;
        return this;
    }

    public String getMainCameraSpec() {
        return mainCameraSpec;
    }

    public MobileDeviceRepository setMainCameraSpec(String mainCameraSpec) {
        this.mainCameraSpec = mainCameraSpec;
        return this;
    }

    public String getMainCameraFeature() {
        return mainCameraFeature;
    }

    public MobileDeviceRepository setMainCameraFeature(String mainCameraFeature) {
        this.mainCameraFeature = mainCameraFeature;
        return this;
    }

    public String getMainCameraVideo() {
        return mainCameraVideo;
    }

    public MobileDeviceRepository setMainCameraVideo(String mainCameraVideo) {
        this.mainCameraVideo = mainCameraVideo;
        return this;
    }

    public Integer getSelfieCameraType() {
        return selfieCameraType;
    }

    public MobileDeviceRepository setSelfieCameraType(Integer selfieCameraType) {
        this.selfieCameraType = selfieCameraType;
        return this;
    }

    public String getSelfieCameraSpec() {
        return selfieCameraSpec;
    }

    public MobileDeviceRepository setSelfieCameraSpec(String selfieCameraSpec) {
        this.selfieCameraSpec = selfieCameraSpec;
        return this;
    }

    public String getSelfieCameraFeature() {
        return selfieCameraFeature;
    }

    public MobileDeviceRepository setSelfieCameraFeature(String selfieCameraFeature) {
        this.selfieCameraFeature = selfieCameraFeature;
        return this;
    }

    public String getSelfieCameraVideo() {
        return selfieCameraVideo;
    }

    public MobileDeviceRepository setSelfieCameraVideo(String selfieCameraVideo) {
        this.selfieCameraVideo = selfieCameraVideo;
        return this;
    }

    public Integer getSoundLoudspeaker() {
        return soundLoudspeaker;
    }

    public MobileDeviceRepository setSoundLoudspeaker(Integer soundLoudspeaker) {
        this.soundLoudspeaker = soundLoudspeaker;
        return this;
    }

    public Integer getSound35mmJack() {
        return sound35mmJack;
    }

    public MobileDeviceRepository setSound35mmJack(Integer sound35mmJack) {
        this.sound35mmJack = sound35mmJack;
        return this;
    }

    public String getSensor() {
        return sensor;
    }

    public MobileDeviceRepository setSensor(String sensor) {
        this.sensor = sensor;
        return this;
    }

    public Integer getRemovableUICC() {
        return removableUICC;
    }

    public MobileDeviceRepository setRemovableUICC(Integer removableUICC) {
        this.removableUICC = removableUICC;
        return this;
    }

    public Integer getRemovableEUICC() {
        return removableEUICC;
    }

    public MobileDeviceRepository setRemovableEUICC(Integer removableEUICC) {
        this.removableEUICC = removableEUICC;
        return this;
    }

    public Integer getNonremovableUICC() {
        return nonremovableUICC;
    }

    public MobileDeviceRepository setNonremovableUICC(Integer nonremovableUICC) {
        this.nonremovableUICC = nonremovableUICC;
        return this;
    }

    public Integer getNonremovableEUICC() {
        return nonremovableEUICC;
    }

    public MobileDeviceRepository setNonremovableEUICC(Integer nonremovableEUICC) {
        this.nonremovableEUICC = nonremovableEUICC;
        return this;
    }

    public Double getLaunchPriceAsianMarket() {
        return launchPriceAsianMarket;
    }

    public MobileDeviceRepository setLaunchPriceAsianMarket(Double launchPriceAsianMarket) {
        this.launchPriceAsianMarket = launchPriceAsianMarket;
        return this;
    }

    public Double getLaunchPriceUSMarket() {
        return launchPriceUSMarket;
    }

    public MobileDeviceRepository setLaunchPriceUSMarket(Double launchPriceUSMarket) {
        this.launchPriceUSMarket = launchPriceUSMarket;
        return this;
    }

    public Double getLaunchPriceEuropeMarket() {
        return launchPriceEuropeMarket;
    }

    public MobileDeviceRepository setLaunchPriceEuropeMarket(Double launchPriceEuropeMarket) {
        this.launchPriceEuropeMarket = launchPriceEuropeMarket;
        return this;
    }

    public Double getLaunchPriceInternationalMarket() {
        return launchPriceInternationalMarket;
    }

    public MobileDeviceRepository setLaunchPriceInternationalMarket(Double launchPriceInternationalMarket) {
        this.launchPriceInternationalMarket = launchPriceInternationalMarket;
        return this;
    }

    public Double getLaunchPriceCambodiaMarket() {
        return launchPriceCambodiaMarket;
    }

    public MobileDeviceRepository setLaunchPriceCambodiaMarket(Double launchPriceCambodiaMarket) {
        this.launchPriceCambodiaMarket = launchPriceCambodiaMarket;
        return this;
    }

    public Double getCustomPriceOfDevice() {
        return customPriceOfDevice;
    }

    public MobileDeviceRepository setCustomPriceOfDevice(Double customPriceOfDevice) {
        this.customPriceOfDevice = customPriceOfDevice;
        return this;
    }

    public String getSourceOfCambodiaMarketPrice() {
        return sourceOfCambodiaMarketPrice;
    }

    public MobileDeviceRepository setSourceOfCambodiaMarketPrice(String sourceOfCambodiaMarketPrice) {
        this.sourceOfCambodiaMarketPrice = sourceOfCambodiaMarketPrice;
        return this;
    }

    public String getReportedGlobalIssue() {
        return reportedGlobalIssue;
    }

    public MobileDeviceRepository setReportedGlobalIssue(String reportedGlobalIssue) {
        this.reportedGlobalIssue = reportedGlobalIssue;
        return this;
    }

    public String getReportedLocalIssue() {
        return reportedLocalIssue;
    }

    public MobileDeviceRepository setReportedLocalIssue(String reportedLocalIssue) {
        this.reportedLocalIssue = reportedLocalIssue;
        return this;
    }

    public Integer getDeviceState() {
        return deviceState;
    }

    public MobileDeviceRepository setDeviceState(Integer deviceState) {
        this.deviceState = deviceState;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public MobileDeviceRepository setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getUserType() {
        return userType;
    }

    public MobileDeviceRepository setUserType(Integer userType) {
        this.userType = userType;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public MobileDeviceRepository setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getTrcApprovalStatus() {
        return trcApprovalStatus;
    }

    public MobileDeviceRepository setTrcApprovalStatus(String trcApprovalStatus) {
        this.trcApprovalStatus = trcApprovalStatus;
        return this;
    }

    public String getTrcApprovedStatus() {
        return trcApprovedStatus;
    }

    public MobileDeviceRepository setTrcApprovedStatus(String trcApprovedStatus) {
        this.trcApprovedStatus = trcApprovedStatus;
        return this;
    }

    public List<AttachedFileInfo> getAttachedFiles() {
        return attachedFiles;
    }

    public MobileDeviceRepository setAttachedFiles(List<AttachedFileInfo> attachedFiles) {
        this.attachedFiles = attachedFiles;
        return this;
    }

    public AuditTrailModel getAuditTrailModel() {
        return auditTrailModel;
    }

    public MobileDeviceRepository setAuditTrailModel(AuditTrailModel auditTrailModel) {
        this.auditTrailModel = auditTrailModel;
        return this;
    }

    @Override
    public String toString() {
        return "MobileDeviceRepository{" +
                "id=" + id +
                ", allocationDate=" + allocationDate +
                ", announceDate=" + announceDate +
                ", bandDetail='" + bandDetail + '\'' +
                ", batteryCapacity=" + batteryCapacity +
                ", batteryCharging='" + batteryCharging + '\'' +
                ", batteryType='" + batteryType + '\'' +
                ", bodyDimension='" + bodyDimension + '\'' +
                ", bodyWeight='" + bodyWeight + '\'' +
                ", brandName='" + brandName + '\'' +
                ", commsBluetooth='" + commsBluetooth + '\'' +
                ", commsGPS='" + commsGPS + '\'' +
                ", commsNFC=" + commsNFC +
                ", commsRadio=" + commsRadio +
                ", commsUSB='" + commsUSB + '\'' +
                ", commsWLAN='" + commsWLAN + '\'' +
                ", createdOn=" + createdOn +
                ", modifiedOn=" + modifiedOn +
                ", deviceId='" + deviceId + '\'' +
                ", marketingName='" + marketingName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", manufacturingLocation='" + manufacturingLocation + '\'' +
                ", modelName='" + modelName + '\'' +
                ", oem='" + oem + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", imeiQuantity=" + imeiQuantity +
                ", simSlot=" + simSlot +
                ", esimSupport=" + esimSupport +
                ", softsimSupport=" + softsimSupport +
                ", simType='" + simType + '\'' +
                ", os='" + os + '\'' +
                ", osBaseVersion='" + osBaseVersion + '\'' +
                ", launchDate=" + launchDate +
                ", deviceStatus='" + deviceStatus + '\'' +
                ", discontinueDate=" + discontinueDate +
                ", networkTechnologyGSM=" + networkTechnologyGSM +
                ", networkTechnologyCDMA=" + networkTechnologyCDMA +
                ", networkTechnologyEVDO=" + networkTechnologyEVDO +
                ", networkTechnologyLTE=" + networkTechnologyLTE +
                ", networkTechnology5G=" + networkTechnology5G +
                ", networkTechnology6G=" + networkTechnology6G +
                ", networkTechnology7G=" + networkTechnology7G +
                ", network2GBand='" + network2GBand + '\'' +
                ", network3GBand='" + network3GBand + '\'' +
                ", network4GBand='" + network4GBand + '\'' +
                ", network5GBand='" + network5GBand + '\'' +
                ", network6GBand='" + network6GBand + '\'' +
                ", network7GBand='" + network7GBand + '\'' +
                ", networkSpeed='" + networkSpeed + '\'' +
                ", displayType='" + displayType + '\'' +
                ", displaySize='" + displaySize + '\'' +
                ", displayResolution='" + displayResolution + '\'' +
                ", displayProtection='" + displayProtection + '\'' +
                ", platformChipset='" + platformChipset + '\'' +
                ", platformCPU='" + platformCPU + '\'' +
                ", platformGPU='" + platformGPU + '\'' +
                ", memoryCardSlot=" + memoryCardSlot +
                ", memoryInternal=" + memoryInternal +
                ", ram='" + ram + '\'' +
                ", mainCameraType=" + mainCameraType +
                ", mainCameraSpec='" + mainCameraSpec + '\'' +
                ", mainCameraFeature='" + mainCameraFeature + '\'' +
                ", mainCameraVideo='" + mainCameraVideo + '\'' +
                ", selfieCameraType=" + selfieCameraType +
                ", selfieCameraSpec='" + selfieCameraSpec + '\'' +
                ", selfieCameraFeature='" + selfieCameraFeature + '\'' +
                ", selfieCameraVideo='" + selfieCameraVideo + '\'' +
                ", soundLoudspeaker=" + soundLoudspeaker +
                ", sound35mmJack=" + sound35mmJack +
                ", sensor='" + sensor + '\'' +
                ", removableUICC=" + removableUICC +
                ", removableEUICC=" + removableEUICC +
                ", nonremovableUICC=" + nonremovableUICC +
                ", nonremovableEUICC=" + nonremovableEUICC +
                ", launchPriceAsianMarket=" + launchPriceAsianMarket +
                ", launchPriceUSMarket=" + launchPriceUSMarket +
                ", launchPriceEuropeMarket=" + launchPriceEuropeMarket +
                ", launchPriceInternationalMarket=" + launchPriceInternationalMarket +
                ", launchPriceCambodiaMarket=" + launchPriceCambodiaMarket +
                ", customPriceOfDevice=" + customPriceOfDevice +
                ", sourceOfCambodiaMarketPrice='" + sourceOfCambodiaMarketPrice + '\'' +
                ", reportedGlobalIssue='" + reportedGlobalIssue + '\'' +
                ", reportedLocalIssue='" + reportedLocalIssue + '\'' +
                ", deviceState=" + deviceState +
                ", userId=" + userId +
                ", userType=" + userType +
                ", remark='" + remark + '\'' +
                ", attachedFiles=" + attachedFiles +
                ", trcApprovedStatus='" + trcApprovedStatus + '\'' +
                ", approvedBy='" + approvedBy + '\'' +
                ", color='" + color + '\'' +
                ", trcApprovalDate='" + trcApprovalDate + '\'' +
                ", isTestImei=" + isTestImei +
                ", osCurrentVersion='" + osCurrentVersion + '\'' +
                ", networkSpecificIdentifier=" + networkSpecificIdentifier +
                ", isTypeApproved='" + isTypeApproved + '\'' +
                ", trcApprovalStatus='" + trcApprovalStatus + '\'' +
                ", action='" + action + '\'' +
                ", auditTrailModel=" + auditTrailModel +
                '}';
    }
}

