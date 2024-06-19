package org.softuni.mobilelele.testutils;

import org.softuni.mobilelele.model.entity.*;
import org.softuni.mobilelele.model.enums.EngineEnum;
import org.softuni.mobilelele.model.enums.TransmissionEnum;
import org.softuni.mobilelele.repository.BrandRepository;
import org.softuni.mobilelele.repository.ExchangeRateRepository;
import org.softuni.mobilelele.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public class TestData {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private BrandRepository brandRepository;

    public void createExchangeRate(String currency, BigDecimal rate) {
        this.exchangeRateRepository.save(new ExchangeRate()
                .setCurrency(currency)
                .setRate(rate));
    }

    public Offer createTestOffer(UserEntity owner) {

        Brand testBrand = brandRepository.save(new Brand()
                .setName("test brand")
                .setModels(List.of(
                        new Model().setName("test model 1"),
                        new Model().setName("test model 2"))));

        Offer offer = new Offer()
                .setModel(testBrand.getModels().get(0))
                .setImageUrl("https://www.google.com")
                .setPrice(BigDecimal.valueOf(1000))
                .setYear(2020)
                .setDescription("test description")
                .setEngine(EngineEnum.DIESEL)
                .setMileage(10000)
                .setTransmission(TransmissionEnum.AUTOMATIC)
                .setSeller(owner)
                .setUuid(UUID.randomUUID());
        return offerRepository.save(offer);
    }

    public void cleanUp() {
        this.exchangeRateRepository.deleteAll();
        this.offerRepository.deleteAll();
        this.brandRepository.deleteAll();
    }
}

