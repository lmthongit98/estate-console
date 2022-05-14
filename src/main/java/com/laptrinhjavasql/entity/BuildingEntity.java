package com.laptrinhjavasql.entity;

import com.laptrinhjavasql.anotation.Column;
import com.laptrinhjavasql.anotation.Entity;
import com.laptrinhjavasql.anotation.Table;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {
	
	@Column(name = "name")
	String name;
	
	@Column(name = "floorarea")
	Integer floorArea;
	
	@Column(name = "numberofbasement")
	Integer numberOfBasement;
	
	@Column(name = "types")
	String types;

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

}
