package org.softuni.mobilelele.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "brands")
@NamedEntityGraph(name = "brandWithModels", attributeNodes = @NamedAttributeNode("models"))
public class Brand extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    private LocalDateTime created;

    private LocalDateTime modified;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
//    @Fetch(FetchMode.SUBSELECT)
    private List<Model> models;

    public Brand() {
    }

    public String getName() {
        return name;
    }

    public Brand setName(String brand) {
        this.name = brand;
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

    public List<Model> getModels() {
        return models;
    }

    public Brand setModels(List<Model> models) {
        this.models = models;
        return this;
    }
}
