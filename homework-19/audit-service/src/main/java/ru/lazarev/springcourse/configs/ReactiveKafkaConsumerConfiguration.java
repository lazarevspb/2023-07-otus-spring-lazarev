package ru.lazarev.springcourse.configs;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import reactor.kafka.receiver.ReceiverOptions;
import ru.lazarev.springcourse.dtos.EventKafkaMessageDto;

import java.util.Map;

import static java.util.Collections.singletonList;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ReactiveKafkaConsumerConfiguration {
    KafkaProperties kafkaProperties;

    @Value("${kafka.audit.topic}")
    @NonFinal
    String topic;

    @Bean
    public ReactiveKafkaConsumerTemplate<String, EventKafkaMessageDto> eventConsumer() {
        return createReactiveKafkaConsumerTemplate(
            kafkaProperties.buildConsumerProperties(),
            topic,
            EventKafkaMessageDto.class
        );
    }

    private <T> ReactiveKafkaConsumerTemplate<String, T> createReactiveKafkaConsumerTemplate(
        Map<String, Object> configProperties,
        String topic,
        Class<T> valueType
    ) {
        ReceiverOptions<String, T> basicReceiverOptions = ReceiverOptions.create(configProperties);
        var deserializer = new ErrorHandlingDeserializer<>(new JsonDeserializer<>(valueType, false));
        deserializer.setFailedDeserializationFunction(failedDeserializationInfo -> {
            log.error("Deserialization kafka value exception, topic: {}", failedDeserializationInfo.getTopic(),
                      failedDeserializationInfo.getException());
            return null;
        });
        ReceiverOptions<String, T> exceptionSafeOptions = basicReceiverOptions.withValueDeserializer(deserializer);
        return new ReactiveKafkaConsumerTemplate<>(exceptionSafeOptions.subscription(singletonList(topic)));
    }
}