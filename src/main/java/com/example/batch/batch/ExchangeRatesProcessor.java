package com.example.batch.batch;

import com.example.batch.batch.models.ExchangeRates;
import com.example.batch.batch.repository.ExchangeRaterRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ExchangeRatesProcessor implements ItemProcessor<ExchangeRates, ExchangeRates> {
    @Autowired
    ExchangeRaterRepository exchangeRaterRepository;

    @Override
    public ExchangeRates process(ExchangeRates exchangeRates) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        String date = exchangeRates.getExchangedate();
        LocalDate localDate = LocalDate.parse(date, formatter);
        localDate.minusDays(1);
        date = localDate.format(formatter);
        ExchangeRates previousExchangeRates = null;
        Optional<ExchangeRates> optionalExchangeRates = exchangeRaterRepository.findFirstByExchangedateAndCc(date, exchangeRates.getCc());
        if (optionalExchangeRates.isPresent()) {
            previousExchangeRates = optionalExchangeRates.get();
        } else return exchangeRates;

        Float difference = exchangeRates.getRate() - previousExchangeRates.getRate();

        exchangeRates.setRateDifference(difference);

        return exchangeRates;
    }
}
