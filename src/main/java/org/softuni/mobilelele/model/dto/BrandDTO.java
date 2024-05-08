package org.softuni.mobilelele.model.dto;

import org.softuni.mobilelele.model.entity.Model;

import java.util.List;

public class BrandDTO {

    private String name;

    private List<Model> models;

    public BrandDTO() {
    }

    public String getName() {
        return name;
    }

    public BrandDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<Model> getModels() {
        return models;
    }

    public BrandDTO setModels(List<Model> models) {
        this.models = models;
        return this;
    }
}
