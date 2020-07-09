package com.asa.api.demo.rest;

import com.asa.api.demo.constant.car.Category;
import com.asa.api.demo.constant.car.Energy;
import com.asa.api.demo.constant.car.Mark;
import com.asa.api.demo.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cars")
public class CarController {

    @PostMapping("add")
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        car.setId(UUID.randomUUID().toString());
        getCarList().add(car);
        return ResponseEntity.ok(car);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getCar(@PathVariable String id) {
       for(Car c : getCarList()) {
           if(c.getId().equals(id)) {
               return ResponseEntity.ok(c);
           }
       }
        return new ResponseEntity<String>("error.car.not.found", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("list")
    public ResponseEntity<List<Car>> getCars() {
       return ResponseEntity.ok(getCarList());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCar(@PathVariable String id , @RequestBody Car car) {
        //TODO
        return new ResponseEntity<String>("error.car.not.found", HttpStatus.BAD_REQUEST);
    }
    private List<Car> getCarList() {
        Car c1= Car.builder()
                .id(UUID.randomUUID().toString())
                .category(Category.Urban.name())
                .mark(Mark.Peugeot.name())
                .model("206")
                .year(2004)
                .energy(Energy.Diesel.name())
                .power(12)
                .build();
        Car c2= Car.builder()
                .id(UUID.randomUUID().toString())
                .category(Category.Sedan.name())
                .mark(Mark.Renault.name())
                .model("R8")
                .year(1983)
                .energy(Energy.Essence.name())
                .power(4)
                .build();
        Car c3= Car.builder()
                .id(UUID.randomUUID().toString())
                .category(Category.Cabriolet.name())
                .mark(Mark.Volkswagen.name())
                .model("Golf")
                .year(2019)
                .energy(Energy.Electric.name())
                .power(125)
                .build();

        return Arrays.asList(c1, c2, c3);
    }

}
