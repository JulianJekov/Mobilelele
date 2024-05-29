package org.softuni.mobilelele.model.dto;

import java.math.BigDecimal;
import java.util.Map;

public class ExchangeRatesDTO {
    
    private String base;
    private Map<String, BigDecimal> rates;

    public String getBase() {
        return base;
    }

    public ExchangeRatesDTO setBase(String base) {
        this.base = base;
        return this;
    }

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public ExchangeRatesDTO setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
        return this;
    }

    @Override
    public String toString() {
        return "ExchangeRatesDTO{" +
                "base='" + base + '\'' +
                ", rates=" + rates +
                '}';
    }
}
