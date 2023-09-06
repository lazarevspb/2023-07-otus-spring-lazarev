package ru.lazarev.springcourse.dao.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.lazarev.springcourse.dao.GenreDao;
import ru.lazarev.springcourse.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreDaoJdbc implements GenreDao {

    JdbcOperations jdbcOperations;

    @Override
    public List<Genre> findAll() {
        String sql = "SELECT id, name FROM genres";
        return jdbcOperations.query(sql, new GenreDaoJdbc.GenreRowMapper());
    }

    private static class GenreRowMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            String name = rs.getString("name");

            Genre genre = new Genre();
            genre.setId(id);
            genre.setName(name);

            return genre;
        }
    }
}
