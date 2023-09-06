package ru.lazarev.springcourse.dao.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.lazarev.springcourse.dao.AuthorDao;
import ru.lazarev.springcourse.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorDaoJdbc implements AuthorDao {
    
    JdbcOperations jdbcOperations;

    @Override
    public List<Author> findAll() {
        String sql = "SELECT id, name FROM authors";
        return jdbcOperations.query(sql, new AuthorDaoJdbc.AuthorRowMapper());
    }

    private static class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            var id = rs.getLong("id");
            var name = rs.getString("name");

            var author = new Author();
            author.setId(id);
            author.setName(name);

            return author;
        }
    }
}
