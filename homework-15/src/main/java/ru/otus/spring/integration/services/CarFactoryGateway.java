package ru.otus.spring.integration.services;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.integration.domain.Car;
import ru.otus.spring.integration.domain.CarBrand;

import java.util.Collection;

@MessagingGateway
public interface CarFactoryGateway {

    @Gateway(requestChannel = "partsChannel", replyChannel = "carChannel")
    Collection<Car> process(Collection<CarBrand> carBrand);
}
