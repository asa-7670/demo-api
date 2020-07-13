package com.asa.api.demo.services;

import com.asa.api.demo.model.Car;
import javax.validation.Valid;
import java.util.List;

public interface CarService {
    List<Car> getCars();
    Car getCarById(String id);
    Car addCar(@Valid Car car);
    boolean deleteCar(String id);
    boolean updateCar(String id, @Valid Car car);
}
