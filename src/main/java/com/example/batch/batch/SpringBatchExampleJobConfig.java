package com.example.batch.batch;

import com.example.batch.batch.models.ExchangeRates;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;


@Configuration
@EnableBatchProcessing
public class SpringBatchExampleJobConfig {


    private static final String PROPERTY_REST_API_URL = "rest.api.url";

    @Bean
    public ItemReader<ExchangeRates> itemReader(Environment environment, RestTemplate restTemplate) {
        return new RESTExchangeRatesReader(environment.getRequiredProperty(PROPERTY_REST_API_URL), restTemplate);
    }
    @Bean
    public ItemWriter<ExchangeRates> itemWriter() {
        return new LoggingItemWriter();
    }

    @Bean
    public ItemProcessor<ExchangeRates, ExchangeRates> itemProcessor(){
        return new ExchangeRatesProcessor();
    }

    @Bean
    public Step exampleJobStep(ItemReader<ExchangeRates> reader,
                               ItemWriter<ExchangeRates> writer,
                               ItemProcessor<ExchangeRates,ExchangeRates> processor,
                               StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("exampleJobStep")
                .<ExchangeRates, ExchangeRates>chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job exampleJob(Step exampleJobStep,
                          JobBuilderFactory jobBuilderFactory) {
        return jobBuilderFactory.get("exampleJob")
                .incrementer(new RunIdIncrementer())
                .flow(exampleJobStep)
                .end()
                .build();
    }
}