package com.manulsoftware.weddings.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "wedding_agency")
@NamedQueries({
		@NamedQuery(name = "WeddingAgency.getAllAgencies", query = "SELECT w FROM WeddingAgency w WHERE w.deleted = false"),
		@NamedQuery(name = "WeddingAgency.getAllCountries", query = "SELECT DISTINCT(w.country) FROM WeddingAgency w ORDER BY w.country ASC") })
public class WeddingAgency {
	
	@Id
	@SequenceGenerator(name="wedding_agency_id_seq", sequenceName="wedding_agency_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="wedding_agency_id_seq")
	private Integer id;
	
	private String name;
	
	private String country;
	
	private String seolink;
	
	private Date created;
	
	private Date updated;
	
	private boolean deleted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSeolink() {
		return seolink;
	}

	public void setSeolink(String seolink) {
		this.seolink = seolink;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
