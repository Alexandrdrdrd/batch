package com.example.batch.batch.repository;

import com.example.batch.batch.models.ExchangeRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ExchangeRaterRepository extends JpaRepository<ExchangeRates, Integer> {
    Optional<ExchangeRates> findFirstByExchangedateAndCc(String exchanDedate, String cc);
}
