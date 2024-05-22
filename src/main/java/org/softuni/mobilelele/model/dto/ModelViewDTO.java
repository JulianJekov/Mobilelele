package org.softuni.mobilelele.model.dto;
import org.softuni.mobilelele.model.enums.ModelCategory;

public class ModelViewDTO {

    private String name;

    private ModelCategory category;

    private Integer startYear;

    private Integer endYear;

    private String imageUrl;

    public ModelViewDTO() {
    }

    public ModelViewDTO(String name, ModelCategory category, Integer startYear, Integer endYear, String imageUrl) {
        this.name = name;
        this.category = category;
        this.startYear = startYear;
        this.endYear = endYear;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public ModelViewDTO setName(String name) {
        this.name = name;
        return this;
    }

    public ModelCategory getCategory() {
        return category;
    }

    public ModelViewDTO setCategory(ModelCategory category) {
        this.category = category;
        return this;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public ModelViewDTO setStartYear(Integer startYear) {
        this.startYear = startYear;
        return this;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public ModelViewDTO setEndYear(Integer endYear) {
        this.endYear = endYear;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ModelViewDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
