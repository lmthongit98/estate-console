package com.laptrinhjavasql.entity;

import com.laptrinhjavasql.anotation.Column;
import com.laptrinhjavasql.anotation.Entity;
import com.laptrinhjavasql.anotation.Table;

@Entity
@Table(name = "rentarea")
public class RentAreaEntity extends BaseEntity {
    @Column(name = "value")
    private Integer value;

    @Column(name = "building_id")
    private Long buildingId;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
}
