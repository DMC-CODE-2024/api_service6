package com.glocks.application.entity.app;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "district_db")
public class DistrictEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_on")
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdOn;

    @Column(name = "modified_on")
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime modifiedOn;


    @Column(name = "district")
    private String district;

    @Column(name = "district_km")
    private String districtKm;

    @Column(name = "province_id")
    private Long provinceId;

    public String getDistrictKm() {
        return districtKm;
    }

    public DistrictEntity setDistrictKm(String districtKm) {
        this.districtKm = districtKm;
        return this;
    }

    public Long getId() {
        return id;
    }

    public DistrictEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public DistrictEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public DistrictEntity setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getDistrict() {
        return district;
    }

    public DistrictEntity setDistrict(String district) {
        this.district = district;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DistrictEntity{");
        sb.append("id=").append(id);
        sb.append(", createdOn=").append(createdOn);
        sb.append(", modifiedOn=").append(modifiedOn);
        sb.append(", district='").append(district).append('\'');
        sb.append(", provinceId='").append(provinceId).append('\'');
        sb.append(", districtKm='").append(districtKm).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
