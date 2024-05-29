package org.softuni.mobilelele.service;

import org.softuni.mobilelele.model.dto.ExchangeRatesDTO;

public interface CurrencyService {

    void refreshRates(ExchangeRatesDTO exchangeRatesDTO);
}
