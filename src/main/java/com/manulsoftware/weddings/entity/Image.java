package com.manulsoftware.weddings.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image {
	@Id
	@SequenceGenerator(name="image_id_seq", sequenceName="image_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="image_id_seq")
	private Integer id;
	
	@Column(name = "agency_id")
	private Integer agencyId;
	
	private String name;
	
	private boolean thumbnail;

	private boolean spread;

	private boolean list;
	
	@Column(name = "related_image_id")
	private Integer relatedImageId;
	
	@Column(name = "content_type")
	private String contentType;
	
	private byte[] content;
	
	public Image() {
		super();
	}

	public Image(Integer id, String name, Integer relatedImageId) {
		super();
		this.id = id;
		this.name = name;
		this.relatedImageId = relatedImageId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Integer getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Integer agencyId) {
		this.agencyId = agencyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(boolean thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Integer getRelatedImageId() {
		return relatedImageId;
	}

	public void setRelatedImageId(Integer relatedImageId) {
		this.relatedImageId = relatedImageId;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public boolean isSpread() {
		return spread;
	}

	public void setSpread(boolean spread) {
		this.spread = spread;
	}

	public boolean isList() {
		return list;
	}

	public void setList(boolean list) {
		this.list = list;
	}
}
