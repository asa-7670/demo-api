package com.asa.api.demo.rest;

import com.asa.api.demo.model.Car;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/demo")
public class DemoRest {

    @PostMapping("add")
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        car.setId(UUID.randomUUID().toString());
        return ResponseEntity.ok(car);
    }
    @GetMapping("{id}")
    public ResponseEntity<Car> getCar(@PathVariable String id) {
        Car car = new Car();
        car.setId(id);
        car.setType("PEUGEOT");
        car.setName("206");
        car.setPower(7);
        return ResponseEntity.ok(car);
    }
}
