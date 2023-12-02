package ru.otus.spring.integration.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.integration.domain.Car;
import ru.otus.spring.integration.domain.CarBrand;

@Service
@Slf4j
public class CarServiceImpl implements CarService {

    private static void delay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car assembling(CarBrand carBrand) {
        log.info("assembling {}", carBrand.getCarBrandName());
        delay();
        log.info("assembling {} done", carBrand.getCarBrandName());
        return new Car(carBrand.getCarBrandName());
    }
}
