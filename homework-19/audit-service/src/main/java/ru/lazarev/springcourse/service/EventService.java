package ru.lazarev.springcourse.service;

import ru.lazarev.springcourse.entities.Event;
import ru.lazarev.springcourse.model.EventKafkaMessage;

import java.util.Optional;

public interface EventService {

    Optional<Event> find(String username);

    Event save(EventKafkaMessage eventKafkaMessage);

}
