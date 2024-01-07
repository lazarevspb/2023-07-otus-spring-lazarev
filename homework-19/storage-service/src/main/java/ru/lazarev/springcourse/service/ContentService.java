package ru.lazarev.springcourse.service;

import ru.lazarev.springcourse.domain.Content;
import ru.lazarev.springcourse.dto.ContentDto;

public interface ContentService {

    Content findContentByBookId(Long bookId);

    void saveContent(ContentDto book);

    void deleteContentByBookId(String id);
}
