package org.softuni.mobilelele.model.dto;

import jakarta.validation.constraints.*;
import org.softuni.mobilelele.model.enums.EngineEnum;
import org.softuni.mobilelele.model.enums.TransmissionEnum;
import org.softuni.mobilelele.validation.YearNotInTheFuture;

import java.math.BigDecimal;

public class UpdateOfferDTO {

    private Long id;

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

    @NotNull(message = "Year must be provided")
    @Min(1930)
    @YearNotInTheFuture(message = "The year should not be in the future!")
    private Integer year;

    @Positive
    @NotNull
    private Integer mileage;

    @NotEmpty
    @Size(min = 5, max = 512)
    private String description;

    @NotEmpty
    private String imageUrl;

    public UpdateOfferDTO() {
    }



    public BigDecimal getPrice() {
        return price;
    }

    public UpdateOfferDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public UpdateOfferDTO setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public UpdateOfferDTO setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public UpdateOfferDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public UpdateOfferDTO setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UpdateOfferDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UpdateOfferDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Long getModelId() {
        return modelId;
    }

    public UpdateOfferDTO setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UpdateOfferDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
