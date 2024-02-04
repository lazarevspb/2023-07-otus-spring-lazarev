package ru.lazarev.springcourse.handler;

import reactor.core.publisher.Mono;
import ru.lazarev.springcourse.model.EventKafkaMessage;

public interface EventMessageHandler {
    Mono<Void> handle(EventKafkaMessage eventKafkaMessage);
}
