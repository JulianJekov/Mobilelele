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


    public List<Model> getModels() {
        return models;
    }

    public Brand setModels(List<Model> models) {
        this.models = models;
        return this;
    }
}
