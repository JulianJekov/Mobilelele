package org.softuni.mobilelele.init;

import org.softuni.mobilelele.config.OpenExchangeRateConfig;
import org.softuni.mobilelele.model.dto.ExchangeRatesDTO;
import org.softuni.mobilelele.service.CurrencyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Component
public class RatesInit implements CommandLineRunner {

    private final OpenExchangeRateConfig openExchangeRateConfig;
    private final RestTemplate restTemplate;
    private final CurrencyService currencyService;

    public RatesInit(OpenExchangeRateConfig openExchangeRateConfig, RestTemplate restTemplate, CurrencyService currencyService) {
        this.openExchangeRateConfig = openExchangeRateConfig;
        this.restTemplate = restTemplate;
        this.currencyService = currencyService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (openExchangeRateConfig.isEnabled()) {
            String openExchangeRateUrl =
                    openExchangeRateConfig.getSchema() +
                            "://" +
                            openExchangeRateConfig.getHost() +
                            openExchangeRateConfig.getPath() +
                            "?app_id={app_id}&symbols={symbols}";

            Map<String, String> requestParams = Map.of(
                    "app_id", openExchangeRateConfig.getAppId(),
                    "symbols", String.join(",", openExchangeRateConfig.getSymbols())
            );

            ExchangeRatesDTO exchangeRatesDTO = restTemplate
                    .getForObject(openExchangeRateUrl, ExchangeRatesDTO.class, requestParams);

            System.out.println(exchangeRatesDTO);

            currencyService.refreshRates(exchangeRatesDTO);
        }
    }
}
