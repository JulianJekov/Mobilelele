package org.softuni.mobilelele.repository;

import org.softuni.mobilelele.model.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, String> {
}
