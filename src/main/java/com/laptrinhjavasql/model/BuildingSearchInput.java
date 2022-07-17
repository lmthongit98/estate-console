package com.laptrinhjavasql.model;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchInput {
    private String name;
    private String street;
    private Integer floorArea;
    private Integer numberOfBasement;
    private List<String> buildingTypes = new ArrayList<>();
    private Integer costRentFrom;
    private Integer costRentTo;
    private Integer areaRentFrom;
    private Integer areaRentTo;
    private Long staffId;
    private String managerName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Integer floorArea) {
        this.floorArea = floorArea;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public List<String> getBuildingTypes() {
        return buildingTypes;
    }

    public void setBuildingTypes(List<String> buildingTypes) {
        this.buildingTypes = buildingTypes;
    }

    public Integer getCostRentFrom() {
        return costRentFrom;
    }

    public void setCostRentFrom(Integer costRentFrom) {
        this.costRentFrom = costRentFrom;
    }

    public Integer getCostRentTo() {
        return costRentTo;
    }

    public void setCostRentTo(Integer costRentTo) {
        this.costRentTo = costRentTo;
    }

    public Integer getAreaRentFrom() {
        return areaRentFrom;
    }

    public void setAreaRentFrom(Integer areaRentFrom) {
        this.areaRentFrom = areaRentFrom;
    }

    public Integer getAreaRentTo() {
        return areaRentTo;
    }

    public void setAreaRentTo(Integer areaRentTo) {
        this.areaRentTo = areaRentTo;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}
