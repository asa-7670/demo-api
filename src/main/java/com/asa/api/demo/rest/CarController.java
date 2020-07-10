package com.asa.api.demo.rest;

import com.asa.api.demo.constant.car.Category;
import com.asa.api.demo.constant.car.Energy;
import com.asa.api.demo.constant.car.Mark;
import com.asa.api.demo.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cars")
public class CarController {

    private List<Car> cars = new ArrayList<>();

    public CarController() {
        initCarList();
    }

    @PostMapping("add")
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        car.setId(UUID.randomUUID().toString());
        cars.add(car);
        return ResponseEntity.ok(car);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getCar(@PathVariable String id) {
       for(Car c : cars) {
           if(c.getId().equals(id)) {
               return ResponseEntity.ok(c);
           }
       }
        return new ResponseEntity<String>("error.car.not.found", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("list")
    public ResponseEntity<List<Car>> getCars() {
       return ResponseEntity.ok(cars);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCar(@PathVariable String id , @RequestBody Car car) {
        //TODO
        return new ResponseEntity<String>("error.car.not.found", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteCar(@PathVariable String id) {
        for(Car car : cars) {
            if( car.getId().equals(id)){
                cars.remove(car);
                break;
            }
        }
        return ResponseEntity.ok(Boolean.TRUE);
    }
    private void initCarList() {
        Car c1= Car.builder()
                .id("198d4e4e-2f78-418d-8996-e246f5e1054f")
                .category(Category.Urban.name())
                .mark(Mark.Peugeot.name())
                .model("206")
                .year(2004)
                .energy(Energy.Diesel.name())
                .power(12)
                .build();
        Car c2= Car.builder()
                .id("db6a5f77-94c0-4bb1-8a66-1316cfe84ae0")
                .category(Category.Sedan.name())
                .mark(Mark.Renault.name())
                .model("R8")
                .year(1983)
                .energy(Energy.Essence.name())
                .power(4)
                .build();
        Car c3= Car.builder()
                .id("fcd238bd-4b72-4b06-b500-872795cc3867")
                .category(Category.Cabriolet.name())
                .mark(Mark.Volkswagen.name())
                .model("Golf")
                .year(2019)
                .energy(Energy.Electric.name())
                .power(125)
                .build();

        cars.addAll(Arrays.asList(c1, c2, c3));
    }

}
