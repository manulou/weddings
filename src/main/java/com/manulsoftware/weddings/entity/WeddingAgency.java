package com.manulsoftware.weddings.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "wedding_agency")
@NamedQueries({
		@NamedQuery(name = "WeddingAgency.getAllAgencies", query = "SELECT w FROM WeddingAgency w WHERE w.deleted = false") })
public class WeddingAgency {
	
	@Id
	@SequenceGenerator(name="wedding_agency_id_seq", sequenceName="wedding_agency_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="wedding_agency_id_seq")
	private Integer id;
	
	private String name;
	
	@Fetch(FetchMode.JOIN)
	@OneToOne()
	@JoinColumn(name = "country_id")
	private Country country;
	
	private String seolink;
	
	private String email;
	
	private String phone;
	
	@Column(name = "min_price")
	private Integer minPrice;
	
	@Column(name = "max_price")
	private Integer maxPrice;
	
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}

	public Integer getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}
}
