package org.softuni.mobilelele.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String brand;

    public Brand() {
    }

    public String getBrand() {
        return brand;
    }

    public Brand setBrand(String brand) {
        this.brand = brand;
        return this;
    }
}
