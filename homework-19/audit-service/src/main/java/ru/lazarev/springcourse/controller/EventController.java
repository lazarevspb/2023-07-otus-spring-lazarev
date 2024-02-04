package ru.lazarev.springcourse.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lazarev.springcourse.mapper.EventMapper;
import ru.lazarev.springcourse.service.EventService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventController {
    EventService eventService;
    EventMapper mapper;

    @GetMapping("/")
    public ResponseEntity<?> createAuthToken() {
        return ResponseEntity.ok(null);
    }
}