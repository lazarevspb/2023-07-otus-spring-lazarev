package ru.lazarev.springcourse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.lazarev.springcourse.dtos.EventDto;
import ru.lazarev.springcourse.dtos.EventKafkaMessageDto;
import ru.lazarev.springcourse.entities.Event;
import ru.lazarev.springcourse.model.EventKafkaMessage;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "userId", expression = "java(getUserIdFromEvent(event))")
    @Mapping(target = "bookId", expression = "java(getBookIdFromEvent(event))")
    @Mapping(target = "operationType", source = "eventType")
    Event map(EventKafkaMessage event);
    EventKafkaMessage map(EventKafkaMessageDto event);

    default Instant map(Long value) {
        return value != null ? Instant.ofEpochMilli(value) : null;
    }

    default Long getUserIdFromEvent(EventKafkaMessage event) {
        return event.getMessage().getUserId();
    }

    default Long getBookIdFromEvent(EventKafkaMessage event) {
        return event.getMessage().getBookId();
    }
}
