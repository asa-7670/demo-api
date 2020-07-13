package com.asa.api.demo.rest;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;

import com.asa.api.demo.constant.car.Category;
import com.asa.api.demo.constant.car.Energy;
import com.asa.api.demo.constant.car.Mark;
import com.asa.api.demo.model.Car;
import com.asa.api.demo.services.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CarControllerTest {
    @Mock
    private CarService carService;
    @Captor
    private ArgumentCaptor<String> idCaptor;
    @Captor
    private ArgumentCaptor<Car> carCaptor;

    @InjectMocks
    private CarController carController;

    @Test
    public void testGetCars() {
        when(carService.getCars()).thenReturn(Arrays.asList(getCar().get()));
        List<Car> cars = carController.getCars();
        assertTrue(!cars.isEmpty());
        verify(carService,times(1)).getCars();
    }
    @Test
    public void testGetCar() {
        when(carService.getCarById(idCaptor.capture())).thenReturn(getCar().get());
        Car car = carController.getCar("id");
        assertEquals(car, getCar().get());
        verify(carService, times(1)).getCarById(idCaptor.getValue());
    }

    @Test
    public void testAddCar() {
        Car car = getCar().get();
        when(carService.addCar(carCaptor.capture())).thenReturn(car);
        Car result = carController.addCar(car);
        assertEquals(result, car);
        verify(carService, times(1)).addCar(carCaptor.getValue());
    }

    @Test
    public void testUpdateCar() {
        Car car = getCar().get();
        car.setId("id");
        when(carService.updateCar(idCaptor.capture(), carCaptor.capture())).thenReturn(Boolean.TRUE);
        boolean result = carController.updateCar("id", car);
        assertTrue(result);
        verify(carService, times(1)).updateCar(idCaptor.getValue(), carCaptor.getValue());
    }

    @Test
    public void testDeleteCar() {
        when(carService.deleteCar(idCaptor.capture())).thenReturn(Boolean.TRUE);
        boolean result = carController.deleteCar("id");
        assertTrue(result);
        verify(carService, times(1)).deleteCar(idCaptor.getValue());
    }


    private Optional<Car> getCar() {
        return Optional.of(Car.builder()
                .category(Category.Urban.name())
                .mark(Mark.Peugeot.name())
                .model("206")
                .energy(Energy.Diesel.getCode())
                .year(2004)
                .power(7)
                .build());
    }
}