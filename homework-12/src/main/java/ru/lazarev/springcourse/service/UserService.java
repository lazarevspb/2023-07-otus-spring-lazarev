package ru.lazarev.springcourse.service;

import ru.lazarev.springcourse.domain.User;

public interface UserService {
    User findByUsername(String username);
}
