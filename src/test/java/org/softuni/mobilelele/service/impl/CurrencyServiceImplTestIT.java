package org.softuni.mobilelele.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.softuni.mobilelele.model.dto.ExchangeRatesDTO;
import org.softuni.mobilelele.model.entity.ExchangeRate;
import org.softuni.mobilelele.repository.ExchangeRateRepository;
import org.softuni.mobilelele.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrencyServiceImplTestIT {

    @Autowired
    private CurrencyService currencyServiceToTest;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @BeforeEach
    void setUp() {
        exchangeRateRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        exchangeRateRepository.deleteAll();
    }

    @ParameterizedTest(name = "Conversion BGN/USD ExRate {0}, expected {1}")
    @MethodSource("testDataBGNtoUSD")
    void testBGNtoUSD(Double exchangeRate, Double expectedValue) {
        ExchangeRatesDTO ExRateTest = new ExchangeRatesDTO();
        ExRateTest.setBase("USD");
        ExRateTest.setRates(Map.of("BGN", BigDecimal.valueOf(exchangeRate)));

        currencyServiceToTest.refreshRates(ExRateTest);

        Optional<ExchangeRate> exRateUSD_BGN = exchangeRateRepository.findById("USD");

        assertTrue(exRateUSD_BGN.isPresent());
        assertEquals(BigDecimal.valueOf(expectedValue).setScale(2, RoundingMode.DOWN),
                exRateUSD_BGN.map(ExchangeRate::getRate).orElseThrow());
    }
    private static Stream<Arguments> testDataBGNtoUSD() {
        return Stream.of(
                Arguments.of(1.840515,0.54),
                Arguments.of(1.752345, 0.57),
                Arguments.of(1.333333, 0.75),
                Arguments.of(1.0, 1.0)
        );
    }
    @ParameterizedTest(name = "Conversion BGN/EUR ExRateBGN {0}, ExRateEUR {1},  expected {2}")
    @MethodSource("testDataBGNtoEUR")
    void testBGNtoEUR(Double exchangeRateBGN, Double exchangeRateEUR,  Double expectedValue) {
        ExchangeRatesDTO ExRateTest = new ExchangeRatesDTO();
        ExRateTest.setBase("USD");
        ExRateTest.setRates(Map.of(
                "BGN", BigDecimal.valueOf(exchangeRateBGN),
                "EUR", BigDecimal.valueOf(exchangeRateEUR))
        );

        currencyServiceToTest.refreshRates(ExRateTest);

        Optional<ExchangeRate> exRateUSD_BGN = exchangeRateRepository.findById("EUR");

        assertTrue(exRateUSD_BGN.isPresent());
        assertEquals(BigDecimal.valueOf(expectedValue).setScale(2, RoundingMode.DOWN),
                exRateUSD_BGN.map(ExchangeRate::getRate).orElseThrow());
    }

    private static Stream<Arguments> testDataBGNtoEUR() {
        return Stream.of(
                Arguments.of(1.840515, 0.937668, 0.51),
                Arguments.of(1.777515, 0.544454 , 0.31),
                Arguments.of(1.0, 1.0, 1.0)
        );
    }
}