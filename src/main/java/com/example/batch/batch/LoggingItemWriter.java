package com.example.batch.batch;

import com.example.batch.batch.models.ExchangeRates;
//import com.example.batch.batch.repository.ExchangeRaterRepository;
import com.example.batch.batch.repository.ExchangeRaterRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.List;

public class LoggingItemWriter implements ItemWriter<ExchangeRates> {
    @Autowired
    ExchangeRaterRepository exchangeRaterRepository;

    @Override
    public void write(List<? extends ExchangeRates> list) throws Exception {

        exchangeRaterRepository.save(list.get(0));

//        ExchangeRates exchangeRates = exchangeRaterRepository.findFirstByExchangedateAndCc(list.get(0).getExchangedate(),
//                list.get(0).getCc()).get();
//
//        System.out.println(exchangeRates);

    }
}
