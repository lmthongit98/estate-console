package com.laptrinhjavasql.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {
    private String name;
    private String district;
    private Integer floorArea;
    private String street;
    private String ward;
    private Integer numberOfBasement;
    private List<String> buildingTypes;
    private Integer costRentFrom;
    private Integer costRentTo;
    private Integer areaRentFrom;
    private Integer areaRentTo;
    private Long staffId;
    private String managerName;

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public String getStreet() {
        return street;
    }

    public String getWard() {
        return ward;
    }

    public List<String> getBuildingTypes() {
        return buildingTypes;
    }

    public Integer getCostRentFrom() {
        return costRentFrom;
    }

    public Integer getCostRentTo() {
        return costRentTo;
    }

    public Integer getAreaRentFrom() {
        return areaRentFrom;
    }

    public Integer getAreaRentTo() {
        return areaRentTo;
    }

    public Long getStaffId() {
        return staffId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    private BuildingSearchBuilder(Builder builder) {
        this.name = builder.name;
        this.district = builder.district;
        this.floorArea = builder.floorArea;
        this.numberOfBasement = builder.numberOfBasement;
        this.street = builder.street;
        this.ward = builder.ward;
        this.buildingTypes = builder.buildingTypes;
        this.costRentFrom = builder.costRentFrom;
        this.costRentTo = builder.costRentTo;
        this.areaRentFrom = builder.areaRentFrom;
        this.areaRentTo = builder.areaRentTo;
        this.staffId = builder.staffId;
        this.managerName = builder.managerName;
    }

    public static class Builder {

        private String name;
        private String district;
        private String street;
        private String ward;
        private Integer floorArea;
        private Integer numberOfBasement;
        private List<String> buildingTypes = new ArrayList<>();
        private Integer costRentFrom;
        private Integer costRentTo;
        private Integer areaRentFrom;
        private Integer areaRentTo;
        private Long staffId;
        private String managerName;



        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDistrict(String district) {
            this.district = district;
            return this;
        }

        public Builder setBuildingArea(Integer floorArea) {
            this.floorArea = floorArea;
            return this;
        }

        public Builder setNumberOfBasement(Integer numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }

        public Builder setBuildingTypes(List<String> buildingTypes) {
            this.buildingTypes = buildingTypes;
            return this;
        }

        public Builder setCostRentFrom(Integer costRentFrom) {
            this.costRentFrom = costRentFrom;
            return this;
        }

        public Builder setCostRentTo(Integer costRentTo) {
            this.costRentTo = costRentTo;
            return this;
        }

        public Builder setAreaRentFrom(Integer areaRentFrom) {
            this.areaRentFrom = areaRentFrom;
            return this;
        }

        public Builder setAreaRentTo(Integer areaRentTo) {
            this.areaRentTo = areaRentTo;
            return this;
        }

        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }

        public Builder setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }

        public BuildingSearchBuilder build() {
            return new BuildingSearchBuilder(this);
        }
    }
}
