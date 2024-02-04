package ru.lazarev.springcourse.consumer.factory;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import ru.lazarev.springcourse.dtos.EventKafkaMessageDto;
import ru.lazarev.springcourse.handler.EventMessageHandler;
import ru.lazarev.springcourse.mapper.EventMapper;
import ru.lazarev.springcourse.service.EventService;


import java.util.Objects;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventKafkaConsumerFactory extends AbstractKafkaConsumerFactory {

    EventService service;
    ReactiveKafkaConsumerTemplate<String, EventKafkaMessageDto> consumerTemplate;
    EventMapper mapper;
    EventMessageHandler handler;


    @Override
    public Flux<Void> buildConsumer() {
        return consumerTemplate
            .receiveAutoAck()
            .doOnNext(this::logReceivedMessage)
            .map(ConsumerRecord::value)
            .filter(Objects::nonNull)
            .map(mapper::map)
            .flatMap(handler::handle)
            .onErrorContinue((throwable, data) -> logError(throwable));
    }
}
