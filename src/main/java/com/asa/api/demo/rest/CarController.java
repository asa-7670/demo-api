package com.asa.api.demo.rest;

import com.asa.api.demo.model.Car;
import com.asa.api.demo.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("list")
    public List<Car> getCars() {
        return carService.getCars();
    }

    @GetMapping("{id}")
    public Car getCar(@PathVariable String id) {
        return carService.getCarById(id);
    }

    @PostMapping("add")
    public Car addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }

    @PutMapping("{id}")
    public boolean updateCar(@PathVariable String id, @RequestBody Car car) {
        return carService.updateCar(id, car);
    }

    @DeleteMapping("{id}")
    public boolean deleteCar(@PathVariable String id) {
        return carService.deleteCar(id);
    }

}
