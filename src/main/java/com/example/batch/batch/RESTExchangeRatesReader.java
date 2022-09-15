package com.example.batch.batch;

import com.example.batch.batch.models.ExchangeRates;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RESTExchangeRatesReader implements ItemReader<ExchangeRates> {


    private final String apiUrl;
    private final RestTemplate restTemplate;
     int nextExchangeRatesDataIndex;
    private List<ExchangeRates> exchangeRatesData;

    public RESTExchangeRatesReader( String apiUrl, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.nextExchangeRatesDataIndex = 0;

    }

    @Override
    public ExchangeRates read() throws Exception {
        if (exchangeRatesIsNotInitialized()) {
            exchangeRatesData = fetchExchangeRatesDataFromAPI();
        }

        ExchangeRates nextExchangeRatesData= null;

        if (nextExchangeRatesDataIndex < exchangeRatesData.size()) {
            nextExchangeRatesData = exchangeRatesData.get(nextExchangeRatesDataIndex);
            nextExchangeRatesDataIndex++;
        }
        else {
            nextExchangeRatesDataIndex = 0;
            exchangeRatesData = null;
        }

        return nextExchangeRatesData;
    }

    private boolean exchangeRatesIsNotInitialized() {
        return this.exchangeRatesData == null;
    }

    private List<ExchangeRates> fetchExchangeRatesDataFromAPI() {
        ResponseEntity<ExchangeRates[]> response = restTemplate.getForEntity(apiUrl,
                ExchangeRates[].class
        );
        ExchangeRates[] exchangeRatesData = response.getBody();
        return Arrays.asList(exchangeRatesData);
    }
}

