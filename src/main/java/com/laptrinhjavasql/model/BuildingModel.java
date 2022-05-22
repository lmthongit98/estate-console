package com.laptrinhjavasql.model;

public class BuildingModel {
	private Long id;
	private String name;
	private String address;
	private Integer numberOfBasement;
	private Integer floorArea;
	private String managerName;
	private String managerPhone;
	private Integer rentPrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "BuildingModel{" +
				"id=" + id +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", numberOfBasement=" + numberOfBasement +
				", floorArea=" + floorArea +
				", managerName='" + managerName + '\'' +
				", managerPhone='" + managerPhone + '\'' +
				", rentPrice=" + rentPrice +
				'}';
	}
}
