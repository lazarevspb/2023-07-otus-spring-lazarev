package ru.otus.spring.integration.services;

import ru.otus.spring.integration.domain.Car;
import ru.otus.spring.integration.domain.Part;

public interface CarService {

	Car assembling(Part part);
}
