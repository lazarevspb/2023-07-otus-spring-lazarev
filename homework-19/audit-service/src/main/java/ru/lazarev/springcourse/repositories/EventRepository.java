package ru.lazarev.springcourse.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.lazarev.springcourse.entities.Event;

import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    Optional<Event> findByUserId(Long userId);
}
