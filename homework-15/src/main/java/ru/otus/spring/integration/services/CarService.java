package ru.otus.spring.integration.services;

import ru.otus.spring.integration.domain.Car;
import ru.otus.spring.integration.domain.CarBrand;

public interface CarService {
    Car assembling(CarBrand carBrand);
}
