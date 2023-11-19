package ru.otus.spring.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannelSpec;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.PollerSpec;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring.integration.domain.Car;
import ru.otus.spring.integration.services.CarService;

@Configuration
public class IntegrationConfig {

    @Bean
    public MessageChannelSpec<?, ?> partsChannel() {
        return MessageChannels.queue(10);
    }

    @Bean
    public MessageChannelSpec<?, ?> carChannel() {
        return MessageChannels.publishSubscribe();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerSpec poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2);
    }

    @Bean
    public IntegrationFlow cafeFlow(CarService carService) {
        return IntegrationFlow.from(partsChannel())
            .split()
            .handle(carService, "assembling")
            .<Car, Car>transform(f -> new Car(f.getName().toUpperCase()))
            .aggregate()
            .channel(carChannel())
            .get();
    }
}
