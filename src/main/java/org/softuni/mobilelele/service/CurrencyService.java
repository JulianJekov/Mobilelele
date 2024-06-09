package org.softuni.mobilelele.service;

import org.softuni.mobilelele.model.dto.ConvertRequestDTO;
import org.softuni.mobilelele.model.dto.ExchangeRatesDTO;
import org.softuni.mobilelele.model.dto.MoneyDTO;

public interface CurrencyService {

    void refreshRates(ExchangeRatesDTO exchangeRatesDTO);

    MoneyDTO convert(ConvertRequestDTO convertRequestDTO);
}
