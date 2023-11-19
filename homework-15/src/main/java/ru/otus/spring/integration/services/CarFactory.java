package ru.otus.spring.integration.services;


import java.util.Collection;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import ru.otus.spring.integration.domain.Car;
import ru.otus.spring.integration.domain.Part;

@MessagingGateway
public interface CarFactory {

	@Gateway(requestChannel = "partsChannel", replyChannel = "carChannel")
	Collection<Car> process(Collection<Part> part);
}
