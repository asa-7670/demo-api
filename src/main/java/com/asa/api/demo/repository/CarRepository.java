package com.asa.api.demo.repository;

import com.asa.api.demo.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository  extends JpaRepository<Car, String> {
}
