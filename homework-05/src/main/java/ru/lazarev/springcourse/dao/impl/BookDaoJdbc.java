package ru.lazarev.springcourse.dao.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.lazarev.springcourse.dao.BookDao;
import ru.lazarev.springcourse.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookDaoJdbc implements BookDao {

    JdbcOperations jdbcOperations;

    @Override
    public List<Book> findAll() {
        String sql = "SELECT id, title, author_id, genre_id FROM books";
        return jdbcOperations.query(sql, new BookRowMapper());
    }

    @Override
    public Book findById(Long id) {
        String sql = "SELECT id, title, author_id, genre_id FROM books WHERE id = ?";
        return jdbcOperations.queryForObject(sql, new Object[]{id}, new BookRowMapper());
    }

    @Override
    public void save(Book book) {
        String sql = "INSERT INTO books (title, author_id, genre_id) VALUES (?, ?, ?)";
        jdbcOperations.update(sql, book.getTitle(), book.getAuthorId(), book.getGenreId());
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE books SET title = ?, author_id = ?, genre_id = ? WHERE id = ?";
        jdbcOperations.update(sql, book.getTitle(), book.getAuthorId(), book.getGenreId(), book.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbcOperations.update(sql, id);
    }

    private static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            var book = new Book();
            book.setId(rs.getLong("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthorId(rs.getLong("author_id"));
            book.setGenreId(rs.getLong("genre_id"));
            return book;
        }
    }
}
