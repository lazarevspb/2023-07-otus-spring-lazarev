package ru.lazarev.springcourse.service;

import ru.lazarev.springcourse.entities.Event;
import ru.lazarev.springcourse.model.EventKafkaMessage;

import java.util.List;
import java.util.Optional;

public interface EventService {

    Optional<List<Event>> findEventsByUserId(Long userId);

    Event save(EventKafkaMessage eventKafkaMessage);

}
