package org.softuni.mobilelele.model.dto;

import org.softuni.mobilelele.model.enums.EngineEnum;
import org.softuni.mobilelele.model.enums.TransmissionEnum;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OfferViewDTO {

    private Long id;

    private String description;

    private EngineEnum engine;

    private String imageUrl;

    private Integer mileage;

    private BigDecimal price;

    private TransmissionEnum transmission;

    private Integer year;

    private String model;

    private String brand;

    private LocalDateTime created;

    private LocalDateTime modified;

    private String seller;

    public OfferViewDTO() {
    }

    public String getDescription() {
        return description;
    }

    public OfferViewDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferViewDTO setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferViewDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferViewDTO setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferViewDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferViewDTO setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferViewDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public String getModel() {
        return model;
    }

    public OfferViewDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public long getId() {
        return id;
    }

    public OfferViewDTO setId(long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public OfferViewDTO setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public OfferViewDTO setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public OfferViewDTO setModified(LocalDateTime modified) {
        this.modified = modified;
        return this;
    }

    public String getSeller() {
        return seller;
    }

    public OfferViewDTO setSeller(String seller) {
        this.seller = seller;
        return this;
    }
}
