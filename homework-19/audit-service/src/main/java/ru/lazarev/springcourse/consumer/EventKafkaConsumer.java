package ru.lazarev.springcourse.consumer;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ru.lazarev.springcourse.consumer.factory.KafkaConsumerFactory;


import java.util.Collection;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ConditionalOnProperty(value = "spring.kafka.enable", matchIfMissing = true)
public class EventKafkaConsumer implements CommandLineRunner {

    Collection<KafkaConsumerFactory> consumerFactories;

    @Override
    public void run(String... args) {
        consumerFactories.forEach(factory -> factory.buildConsumer().subscribe());
    }
}
