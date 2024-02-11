package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.entities.Event;
import ru.lazarev.springcourse.mapper.EventMapper;
import ru.lazarev.springcourse.model.EventKafkaMessage;
import ru.lazarev.springcourse.repositories.EventRepository;
import ru.lazarev.springcourse.service.EventService;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    EventRepository eventRepository;
    EventMapper mapper;

    @Override
    public Optional<List<Event>> findEventsByUserId(Long userId) {
        return eventRepository.findEventsByUserId(userId);
    }

    @Override
    public Event save(EventKafkaMessage eventKafkaMessage) {
        return eventRepository.save(mapper.map(eventKafkaMessage));
    }
}
