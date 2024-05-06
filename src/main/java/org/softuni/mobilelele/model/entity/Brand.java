package org.softuni.mobilelele.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String brand;

    private LocalDateTime created;

    private LocalDateTime modified;

    public Brand() {
    }

    public String getBrand() {
        return brand;
    }

    public Brand setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Brand setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public Brand setModified(LocalDateTime modified) {
        this.modified = modified;
        return this;
    }
}
