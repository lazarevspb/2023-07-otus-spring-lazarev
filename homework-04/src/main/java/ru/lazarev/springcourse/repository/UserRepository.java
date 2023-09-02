package ru.lazarev.springcourse.repository;

import ru.lazarev.springcourse.domain.User;

import java.util.UUID;

public interface UserRepository {

    User save(User user);

    User getById(UUID uuid);
}
