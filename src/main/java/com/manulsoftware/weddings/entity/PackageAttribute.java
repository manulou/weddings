package com.manulsoftware.weddings.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Vlad on 08/05/2016.
 */
@Entity
@Table(name = "package_attribute")
public class PackageAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Fetch(FetchMode.JOIN)
    @OneToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @Fetch(FetchMode.JOIN)
    @OneToOne()
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wedding_package_id")
    private WeddingPackage weddingPackage;

    private BigDecimal price;

    public WeddingPackage getWeddingPackage() {
        return weddingPackage;
    }

    public void setWeddingPackage(WeddingPackage weddingPackage) {
        this.weddingPackage = weddingPackage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
}
