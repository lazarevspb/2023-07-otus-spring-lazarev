package ru.lazarev.springcourse.handler.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.lazarev.springcourse.handler.EventMessageHandler;
import ru.lazarev.springcourse.model.EventKafkaMessage;
import ru.lazarev.springcourse.service.EventService;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventMessageHandlerImpl implements EventMessageHandler {

    EventService eventService;
    public Mono<Void> handle(EventKafkaMessage eventKafkaMessage) {

        System.out.println("eventKafkaMessage = " + eventKafkaMessage);
        eventService.save(eventKafkaMessage);
        return Mono.empty();
    }
}
