package ru.lazarev.springcourse.library.service;

import ru.lazarev.springcourse.library.dto.ContentDto;

public interface ContentService {
    ContentDto getContent(Long bookId, Long userId);
}
