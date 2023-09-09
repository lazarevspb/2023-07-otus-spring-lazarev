package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.springcourse.dao.BookDao;
import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.dto.BookDto;
import ru.lazarev.springcourse.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {

    BookDao bookDao;

    @Override
    public List<BookDto> findAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public BookDto findBookById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    public void saveBook(Book book) {
        bookDao.save(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.update(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookDao.delete(id);
    }
}
