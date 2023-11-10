package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lazarev.springcourse.postgres.Comment;
import ru.lazarev.springcourse.postgres.PostgresBook;
import ru.lazarev.springcourse.repository.BookRepository;
import ru.lazarev.springcourse.service.BookService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {

    BookRepository repository;

    @Override
    @Transactional
    public List<PostgresBook> findAllBooks() {
        var bookList = repository.findAll();
        bookList.sort(Comparator.comparing(PostgresBook::getId));
        return bookList;
    }

    @Override
    public List<Comment> findAllCommentByBookId(Long id) {
        return repository.findById(id).get().getComments().stream()
            .collect(Collectors.toList());
    }

    @Override
    public PostgresBook findBookById(Long id) {
        return repository.findById(id).get();
    }

    private List<Comment> getOldComments(Optional<PostgresBook> oldBook) {
        return oldBook.map(PostgresBook::getComments).orElse(null);
    }


}
