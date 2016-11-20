package com.manulsoftware.weddings.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country {
	
	@Id
	private Short id;
	
	private String code;
	
	private String name;

	@Column(name = "has_agencies")
	private boolean hasAgencies;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isHasAgencies() {
		return hasAgencies;
	}

	public void setHasAgencies(boolean hasAgencies) {
		this.hasAgencies = hasAgencies;
	}
}
