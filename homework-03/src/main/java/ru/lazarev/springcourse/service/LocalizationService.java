package ru.lazarev.springcourse.service;

public interface LocalizationService {
    String getMessage(String key, Object ...args);
}
