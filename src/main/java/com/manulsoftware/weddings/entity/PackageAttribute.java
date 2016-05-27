package com.manulsoftware.weddings.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private WeddingPackage weddingPackage;

    public WeddingPackage getWeddingPackage() {
        return weddingPackage;
    }

    public void setWeddingPackage(WeddingPackage weddingPackage) {
        this.weddingPackage = weddingPackage;
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
