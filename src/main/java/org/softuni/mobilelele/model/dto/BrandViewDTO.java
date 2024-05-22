package org.softuni.mobilelele.model.dto;

import java.util.List;

public class BrandViewDTO {
    private String name;
    private List<ModelViewDTO> models;

    public BrandViewDTO() {
    }

    public BrandViewDTO(String name, List<ModelViewDTO> models) {
        this.name = name;
        this.models = models;
    }

    public String getName() {
        return name;
    }

    public BrandViewDTO setName(String name) {
        this.name = name;
        return this;
    }


    public List<ModelViewDTO> getModels() {
        return models;
    }

    public BrandViewDTO setModels(List<ModelViewDTO> models) {
        this.models = models;
        return this;
    }
}
