package ru.lazarev.springcourse.service;

import ru.lazarev.springcourse.domain.User;

import java.util.UUID;

public interface UserService {

    User save(User user);

    User getById(UUID uuid);
}
