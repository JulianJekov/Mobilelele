package org.softuni.mobilelele.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softuni.mobilelele.model.dto.ExchangeRatesDTO;
import org.softuni.mobilelele.model.entity.ExchangeRate;
import org.softuni.mobilelele.repository.ExchangeRateRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;


@Service
public class CurrencyServiceImpl implements CurrencyService {

    public static final Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    private final ExchangeRateRepository exchangeRateRepository;

    public CurrencyServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public void refreshRates(ExchangeRatesDTO exchangeRatesDTO) {

        LOGGER.info("Exchange rates received {}", exchangeRatesDTO);

        BigDecimal BGN_TO_USD = getExchangeRate(exchangeRatesDTO, "BGN", "USD").orElse(null);
        BigDecimal BGN_TO_EUR = getExchangeRate(exchangeRatesDTO, "BGN", "EUR").orElse(null);

        if (BGN_TO_USD != null) {
            ExchangeRate exchangeRate = new ExchangeRate().setCurrency("USD").setRate(BGN_TO_USD);
            exchangeRateRepository.save(exchangeRate);
        } else {
            LOGGER.error("Unable to get exchange rate fro BGN TO USD");
        }

        if (BGN_TO_EUR != null) {
            ExchangeRate exchangeRate = new ExchangeRate().setCurrency("EUR").setRate(BGN_TO_EUR);
            exchangeRateRepository.save(exchangeRate);
        } else {
            LOGGER.error("Unable to get exchange rate fro BGN TO EUR");
        }

        LOGGER.info("Rates refreshed...");
    }

    private static Optional<BigDecimal> getExchangeRate(ExchangeRatesDTO exchangeRatesDTO, String from, String to) {

        Objects.requireNonNull(from, "'From' currency cannot be null");
        Objects.requireNonNull(to, "'To' currency cannot be null");

        if (Objects.equals(from, to)) {
            return Optional.of(BigDecimal.ONE);
        }

        if (from.equals(exchangeRatesDTO.getBase())) {
            if (exchangeRatesDTO.getRates().containsKey(to)) {
                return Optional.of(exchangeRatesDTO.getRates().get(to));
            }

        } else if (Objects.equals(to, exchangeRatesDTO.getBase())) {
            return Optional.of(BigDecimal.ONE.divide(exchangeRatesDTO.getRates().get(from),
                    3, RoundingMode.DOWN));

        } else if (exchangeRatesDTO.getRates().containsKey(from) &&
                exchangeRatesDTO.getRates().containsKey(to)) {
            return Optional.of(
                    exchangeRatesDTO.getRates().get(to).
                            divide(exchangeRatesDTO.getRates().get(from),
                                    3, RoundingMode.DOWN));
        }
        return Optional.empty();

    }
}