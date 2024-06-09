package org.softuni.mobilelele.testutils;

import org.softuni.mobilelele.model.entity.ExchangeRate;
import org.softuni.mobilelele.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TestData {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public void createExchangeRate(String currency, BigDecimal rate) {
        this.exchangeRateRepository.save(new ExchangeRate()
                .setCurrency(currency)
                .setRate(rate));
    }

    public void cleanAllTestData() {
        this.exchangeRateRepository.deleteAll();
    }
}

