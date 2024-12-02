package com.glocks.application.entity.app;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "commune_db")
public class CommuneEntity {

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

    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "commune")
    private String commune;

    @Column(name = "commune_km")
    private String communeKm;

    public String getCommuneKm() {
        return communeKm;
    }

    public CommuneEntity setCommuneKm(String communeKm) {
        this.communeKm = communeKm;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CommuneEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public CommuneEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public CommuneEntity setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getCommune() {
        return commune;
    }

    public CommuneEntity setCommune(String commune) {
        this.commune = commune;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommuneEntity{");
        sb.append("id=").append(id);
        sb.append(", createdOn=").append(createdOn);
        sb.append(", modifiedOn=").append(modifiedOn);
        sb.append(", districtId=").append(districtId);
        sb.append(", commune='").append(commune).append('\'');
        sb.append(", communeKm='").append(communeKm).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
