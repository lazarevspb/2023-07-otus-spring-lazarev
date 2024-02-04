package ru.lazarev.springcourse.consumer.factory;

import reactor.core.publisher.Flux;

public interface KafkaConsumerFactory {
    Flux<Void> buildConsumer();
}
