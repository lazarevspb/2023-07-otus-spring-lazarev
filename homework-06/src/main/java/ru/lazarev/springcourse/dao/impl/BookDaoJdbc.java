package ru.lazarev.springcourse.dao.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.lazarev.springcourse.dao.BookDao;
import ru.lazarev.springcourse.domain.Book;
import ru.lazarev.springcourse.dto.AuthorDto;
import ru.lazarev.springcourse.dto.BookDto;
import ru.lazarev.springcourse.dto.GenreDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookDaoJdbc implements BookDao {

    JdbcOperations jdbcOperations;

    @Override
    public List<BookDto> findAll() {
        var sql = "SELECT b.id AS book_id, b.title AS book_title, a.id AS author_id, a.name AS author_name, g.id AS genre_id, g.name AS genre_name " +
            "FROM books b " +
            "INNER JOIN authors a ON b.author_id = a.id " +
            "INNER JOIN genres g ON b.genre_id = g.id";
        return jdbcOperations.query(sql, new BookRowMapper());
    }

    @Override
    public BookDto findById(Long id) {
        var sql = "SELECT b.id AS book_id, b.title AS book_title, a.id AS author_id, a.name AS author_name, g.id AS genre_id, g.name AS genre_name " +
            "FROM books b " +
            "INNER JOIN authors a ON b.author_id = a.id " +
            "INNER JOIN genres g ON b.genre_id = g.id " +
            "WHERE b.id = ?";
        return jdbcOperations.queryForObject(sql, new Object[]{id}, new BookRowMapper());
    }

    @Override
    public void save(Book book) {
        var sql = "INSERT INTO books (title, author_id, genre_id) VALUES (?, ?, ?)";
        jdbcOperations.update(sql, book.getTitle(), book.getAuthorId(), book.getGenreId());
    }

    @Override
    public void update(Book book) {
        var sql = "UPDATE books SET title = ?, author_id = ?, genre_id = ? WHERE id = ?";
        jdbcOperations.update(sql, book.getTitle(), book.getAuthorId(), book.getGenreId(), book.getId());
    }

    @Override
    public void delete(Long id) {
        var sql = "DELETE FROM books WHERE id = ?";
        jdbcOperations.update(sql, id);
    }

    private static class BookRowMapper implements RowMapper<BookDto> {

        @Override
        public BookDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            var book = new BookDto();
            book.setId(rs.getLong("book_id"));
            book.setTitle(rs.getString("book_title"));

            var author = new AuthorDto();
            author.setId(rs.getLong("author_id"));
            author.setName(rs.getString("author_name"));
            book.setAuthor(author);

            var genre = new GenreDto();
            genre.setId(rs.getLong("genre_id"));
            genre.setName(rs.getString("genre_name"));
            book.setGenre(genre);

            return book;
        }
    }
}
