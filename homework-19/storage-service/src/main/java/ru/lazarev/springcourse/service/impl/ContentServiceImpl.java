package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.domain.Content;
import ru.lazarev.springcourse.dto.ContentDto;
import ru.lazarev.springcourse.repository.ContentRepository;
import ru.lazarev.springcourse.service.ContentService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ContentServiceImpl implements ContentService {

    ContentRepository repository;

    @Override
    public Content findContentByBookId(Long id) {
        return repository.findContentByBookId(id);
    }

    @Override
    public void saveContent(ContentDto content) {
        repository.save(new Content()); // TODO: 07.01.2024
    }

    @Override
    public void deleteContentByBookId(String id) {
        repository.findById(id)
            .ifPresent(repository::delete);
    }
}
