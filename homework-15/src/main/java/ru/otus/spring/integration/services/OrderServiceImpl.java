package ru.otus.spring.integration.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import ru.otus.spring.integration.domain.Car;
import ru.otus.spring.integration.domain.Part;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private static final String[] PARTS = {"engine", "wheels", "glass", "door", "seat", "armrest", "abs"};

    private final CarFactoryGateway carFactoryGateway;

    public OrderServiceImpl(CarFactoryGateway carFactoryGateway) {
        this.carFactoryGateway = carFactoryGateway;
    }

    @Override
    public void startGenerateOrdersLoop() {
        Collection<Part> items = generateOrderItems();
        log.info("New production order: {}",
                 items.stream().map(Part::getPartName)
                     .collect(Collectors.joining(",")));
        Collection<Car> car = carFactoryGateway.process(items);
        log.info("Ready car: {}", car.stream()
            .map(Car::getName)
            .collect(Collectors.joining(",")));

    }

    private static Part generateOrderItem() {
        return new Part(PARTS[RandomUtils.nextInt(0, PARTS.length)]);
    }

    private static Collection<Part> generateOrderItems() {
        List<Part> items = new ArrayList<>();
        for (int i = 0; i < RandomUtils.nextInt(1, 5); ++i) {
            items.add(generateOrderItem());
        }
        return items;
    }

    private void delay() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
