package ru.lazarev.springcourse.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.lazarev.springcourse.mapper.EventMapper;
import ru.lazarev.springcourse.service.EventService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventController {
    EventService eventService;
    EventMapper mapper;

    @GetMapping("/events/{userId}")
    public ResponseEntity<?> getEventsByUserId(@PathVariable("userId") Long userId) {
        return eventService.findEventsByUserId(userId).map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}