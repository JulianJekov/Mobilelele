package org.softuni.mobilelele.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.softuni.mobilelele.model.enums.ModelCategory;

import java.time.LocalDateTime;

@Entity
@Table(name = "models")
public class Model extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private ModelCategory category;

    @Length(min = 8, max = 512)
    private String imageUrl;

    private Integer startYear;

    private Integer endYear;

    private LocalDateTime created;

    private LocalDateTime modified;

    @ManyToOne
    private Brand brand;

    public Model() {
    }

    public String getName() {
        return name;
    }

    public Model setName(String name) {
        this.name = name;
        return this;
    }

    public ModelCategory getCategory() {
        return category;
    }

    public Model setCategory(ModelCategory category) {
        this.category = category;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Model setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public Model setStartYear(Integer startYear) {
        this.startYear = startYear;
        return this;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public Model setEndYear(Integer endYear) {
        this.endYear = endYear;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Model setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public Model setModified(LocalDateTime modified) {
        this.modified = modified;
        return this;
    }

    public Brand getBrand() {
        return brand;
    }

    public Model setBrand(Brand brand) {
        this.brand = brand;
        return this;
    }
}
