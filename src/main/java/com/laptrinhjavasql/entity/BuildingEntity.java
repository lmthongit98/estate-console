package com.laptrinhjavasql.entity;

import com.laptrinhjavasql.anotation.Column;
import com.laptrinhjavasql.anotation.Entity;
import com.laptrinhjavasql.anotation.Table;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {
	@Column(name = "name")
	private String name;

	@Column(name = "street")
	private String street;

	@Column(name = "ward")
	private String ward;

	@Column(name = "numberofbasement")
	private Integer numberOfBasement;

	@Column(name = "floorarea")
	private Integer floorArea;

	@Column(name = "managername")
	private String managerName;

	@Column(name = "managerphone")
	private String managerPhone;

	@Column(name = "rentprice")
	private Integer rentPrice;

	@Column(name = "district_id")
	private Long districtId;

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

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public Integer getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(Integer rentPrice) {
		this.rentPrice = rentPrice;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	@Override
	public String toString() {
		return "BuildingEntity{" +
				"name='" + name + '\'' +
				", street='" + street + '\'' +
				", ward='" + ward + '\'' +
				", numberOfBasement=" + numberOfBasement +
				", floorArea=" + floorArea +
				", managerName='" + managerName + '\'' +
				", managerPhone='" + managerPhone + '\'' +
				", rentPrice=" + rentPrice +
				", districtId=" + districtId +
				'}';
	}
}
