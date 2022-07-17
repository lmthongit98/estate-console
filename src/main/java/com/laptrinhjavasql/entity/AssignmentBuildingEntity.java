package com.laptrinhjavasql.entity;

import com.laptrinhjavasql.anotation.Column;
import com.laptrinhjavasql.anotation.Entity;
import com.laptrinhjavasql.anotation.Table;

@Entity
@Table(name = "assignment_building")
public class AssignmentBuildingEntity extends BaseEntity {
    @Column(name = "staff_id")
    private Long staffId;

    @Column(name = "building_id")
    private Long buildingId;

    public  AssignmentBuildingEntity() {
    }

    public AssignmentBuildingEntity(Long staffId, Long buildingId) {
        this.staffId = staffId;
        this.buildingId = buildingId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
}
