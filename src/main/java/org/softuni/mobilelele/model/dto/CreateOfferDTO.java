package org.softuni.mobilelele.model.dto;

import jakarta.validation.constraints.*;
import org.softuni.mobilelele.model.entity.Model;
import org.softuni.mobilelele.model.enums.EngineEnum;
import org.softuni.mobilelele.model.enums.TransmissionEnum;

import java.math.BigDecimal;

public class CreateOfferDTO {

    @Positive
    @NotNull
    private Long modelId;

    @Positive
    @NotNull
    private BigDecimal price;

    @NotNull
    private EngineEnum engine;

    @NotNull
    private TransmissionEnum transmission;

    @NotNull
    @Min(1930)
    private Integer year;

    @Positive
    @NotNull
    private Integer mileage;

    @NotEmpty
    @Size(min = 5, max = 512)
    private String description;

    @NotEmpty
    private String imageUrl;

    public CreateOfferDTO() {
    }



    public BigDecimal getPrice() {
        return price;
    }

    public CreateOfferDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public CreateOfferDTO setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public CreateOfferDTO setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public CreateOfferDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public CreateOfferDTO setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateOfferDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CreateOfferDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Long getModelId() {
        return modelId;
    }

    public CreateOfferDTO setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }
}
