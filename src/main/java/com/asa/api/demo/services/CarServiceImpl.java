package com.asa.api.demo.services;

import com.asa.api.demo.constant.MessageErrorCode;
import com.asa.api.demo.exception.ResourceException;
import com.asa.api.demo.model.Car;
import com.asa.api.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Validated
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(String id) {
         Assert.hasText(id, MessageErrorCode.REQUIRED.getCode());
        return findById(id).get();
    }

    @Override
    public Car addCar(@Valid Car car) {
        Assert.notNull(car, MessageErrorCode.REQUIRED.getCode());
        car.setId(UUID.randomUUID().toString());
        return carRepository.save(car);
    }

    @Override
    public boolean deleteCar(String id) {
        Assert.hasText(id, "error.required");
        Optional<Car> car = findById(id);
        carRepository.delete(car.get());
        return Boolean.TRUE;
    }

    @Override
    public boolean updateCar(String id, Car car) {
        Assert.hasText(id, MessageErrorCode.REQUIRED.getCode());
        findById(id);
        carRepository.save(car);
        return Boolean.TRUE;
    }

    private Optional<Car> findById(String id) {
        return Optional.ofNullable(carRepository.findById(id)
                .orElseThrow(() -> new ResourceException(MessageErrorCode.NOT_FOUND.getCode())));
    }
}
