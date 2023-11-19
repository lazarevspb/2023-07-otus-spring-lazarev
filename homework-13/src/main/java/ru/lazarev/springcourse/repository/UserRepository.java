package ru.lazarev.springcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lazarev.springcourse.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
