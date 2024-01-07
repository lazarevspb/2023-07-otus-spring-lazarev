package ru.lazarev.springcourse.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lazarev.springcourse.mapper.ContentMapper;
import ru.lazarev.springcourse.service.ContentService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/content")
public class ContentController {
    ContentService contentService;
    ContentMapper contentMapper;

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getContent(@PathVariable Long bookId) {
        return ResponseEntity.ok(contentMapper.map(contentService.findContentByBookId(bookId)));
    }
}
