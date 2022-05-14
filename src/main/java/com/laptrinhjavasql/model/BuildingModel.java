package com.laptrinhjavasql.model;

public class BuildingModel {
	private Long id;
	private String name;
	private Integer floorArea;
	private Integer numberOfBasement;
	private String types;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BuildingModel [id=" + id + ", name=" + name + ", floorArea=" + floorArea + ", numberOfBasement="
				+ numberOfBasement + ", types=" + types + "]";
	}

}
