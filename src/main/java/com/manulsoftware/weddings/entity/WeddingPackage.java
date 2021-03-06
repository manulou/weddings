package com.manulsoftware.weddings.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Vlad on 08/05/2016.
 */
@Entity
@Table(name = "wedding_package")
public class WeddingPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "wedding_agency_id")
    @JsonBackReference
    private WeddingAgency weddingAgency;

    private String name;

    private String description;

    private BigDecimal price;

    private boolean visible;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "weddingPackage")
    @JsonManagedReference
    private List<PackageAttribute> attributes;

    private Date created;

    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "all_attributes")
    private String allAttributes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WeddingAgency getWeddingAgency() {
        return weddingAgency;
    }

    public void setWeddingAgency(WeddingAgency weddingAgency) {
        this.weddingAgency = weddingAgency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<PackageAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<PackageAttribute> attributes) {
        this.attributes = attributes;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getAllAttributes() {
        return allAttributes;
    }

    public void setAllAttributes(String allAttributes) {
        this.allAttributes = allAttributes;
    }
}
