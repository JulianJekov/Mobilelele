package org.softuni.mobilelele.service.impl;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.softuni.mobilelele.service.MonitoringService;
import org.springframework.stereotype.Service;

@Service
public class MonitoringServiceImpl implements MonitoringService {

    private final Counter offerSearches;

    public MonitoringServiceImpl(MeterRegistry meterRegistry) {
        this.offerSearches = Counter.builder("offer_search_count")
                .description("How maney offers searched we have performed")
                .register(meterRegistry);
    }

    @Override
    public void logOfferSearch() {
        this.offerSearches.increment();
    }
}
