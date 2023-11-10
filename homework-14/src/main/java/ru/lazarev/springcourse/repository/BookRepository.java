package ru.lazarev.springcourse.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lazarev.springcourse.postgres.PostgresBook;

import java.util.List;

public interface BookRepository extends CrudRepository<PostgresBook, Long> {

    List<PostgresBook> findAll();
}
