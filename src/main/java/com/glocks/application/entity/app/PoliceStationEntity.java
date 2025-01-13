package com.glocks.application.entity.app;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "police_station")
public class PoliceStationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Schema(hidden = true)
    @Column(name = "created_on")
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdOn;
    @Schema(hidden = true)
    @Column(name = "modified_on")
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifiedOn;
    @Column(name = "commune_id")
    private Integer communeId;
    @Column(name = "police")
    private String police;
    @Column(name = "police_km")
    private String policeKm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getCommuneId() {
        return communeId;
    }

    public void setCommuneId(Integer communeId) {
        this.communeId = communeId;
    }

    public String getPolice() {
        return police;
    }

    public void setPolice(String police) {
        this.police = police;
    }

    public String getPoliceKm() {
        return policeKm;
    }

    public void setPoliceKm(String policeKm) {
        this.policeKm = policeKm;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PoliceStationEntity{");
        sb.append("id=").append(id);
        sb.append(", createdOn=").append(createdOn);
        sb.append(", modifiedOn=").append(modifiedOn);
        sb.append(", communeId=").append(communeId);
        sb.append(", police='").append(police).append('\'');
        sb.append(", policeKm='").append(policeKm).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
