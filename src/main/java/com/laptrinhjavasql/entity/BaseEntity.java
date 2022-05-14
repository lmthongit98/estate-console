package com.laptrinhjavasql.entity;

import com.laptrinhjavasql.anotation.Column;
import com.laptrinhjavasql.anotation.Entity;

@Entity
public class BaseEntity {
	
	@Column(name = "id")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
