package ru.otus.spring.integration.services;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.otus.spring.integration.domain.Car;
import ru.otus.spring.integration.domain.Part;

@Service
@Slf4j
public class CarServiceImpl implements CarService {

	@Override
	public Car assembling(Part part) {
		log.info("assembling {}", part.getPartName());
		delay();
		log.info("assembling {} done", part.getPartName());
		return new Car(part.getPartName());
	}

	private static void delay() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
