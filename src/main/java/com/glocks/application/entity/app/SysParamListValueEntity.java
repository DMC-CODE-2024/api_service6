package com.glocks.application.entity.app;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "sys_param_list_value")
public class SysParamListValueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "created_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdOn;

    @Column(name = "description")
    private String description;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "interpretation")
    private String interpretation;

    @Column(name = "list_order")
    private int listOrder;

    @Column(name = "modified_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime modifiedOn;

    @Column(name = "tag")
    private String tag;

    @Column(name = "tag_id")
    private String tagId;

    @Column(name = "value")
    private String value;

    @Column(name = "modified_by")
    private String modifiedBy;

    public int getId() {
        return id;
    }

    public SysParamListValueEntity setId(int id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public SysParamListValueEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SysParamListValueEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public SysParamListValueEntity setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public SysParamListValueEntity setInterpretation(String interpretation) {
        this.interpretation = interpretation;
        return this;
    }

    public int getListOrder() {
        return listOrder;
    }

    public SysParamListValueEntity setListOrder(int listOrder) {
        this.listOrder = listOrder;
        return this;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public SysParamListValueEntity setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public SysParamListValueEntity setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getTagId() {
        return tagId;
    }

    public SysParamListValueEntity setTagId(String tagId) {
        this.tagId = tagId;
        return this;
    }

    public String getValue() {
        return value;
    }

    public SysParamListValueEntity setValue(String value) {
        this.value = value;
        return this;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public SysParamListValueEntity setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SysParamListValueEntity{");
        sb.append("id=").append(id);
        sb.append(", createdOn=").append(createdOn);
        sb.append(", description='").append(description).append('\'');
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", interpretation='").append(interpretation).append('\'');
        sb.append(", listOrder=").append(listOrder);
        sb.append(", modifiedOn=").append(modifiedOn);
        sb.append(", tag='").append(tag).append('\'');
        sb.append(", tagId='").append(tagId).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append(", modifiedBy='").append(modifiedBy).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
