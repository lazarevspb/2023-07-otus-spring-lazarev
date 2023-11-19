package ru.otus.spring.integration.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import ru.otus.spring.integration.domain.Car;
import ru.otus.spring.integration.domain.CarBrand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements OrderService {
    private static final String[] OURS_PERFECT_BRANDS = {"lada", "niva", "moskvich", "gaz", "uaz", "chaika", "e-mobil"};
    CarFactoryGateway carFactoryGateway;

    public OrderServiceImpl(CarFactoryGateway carFactoryGateway) {
        this.carFactoryGateway = carFactoryGateway;
    }

    private static CarBrand generateOrderItem() {
        return new CarBrand(OURS_PERFECT_BRANDS[RandomUtils.nextInt(0, OURS_PERFECT_BRANDS.length)]);
    }

    private static Collection<CarBrand> generateOrderList() {
        var carOrderList = new ArrayList<CarBrand>();
        for (int i = 0; i < RandomUtils.nextInt(1, 5); ++i) {
            carOrderList.add(generateOrderItem());
        }
        return carOrderList;
    }

    @Override
    public void startGenerateOrdersLoop() {
        var items = generateOrderList();
        log.info("New production order: {}",
                 items.stream().map(CarBrand::getCarBrandName)
                     .collect(Collectors.joining(",")));
        var car = carFactoryGateway.process(items);
        log.info("Ready car: {}", car.stream()
            .map(Car::getName)
            .collect(Collectors.joining(",")));
    }
}
