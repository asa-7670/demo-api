package com.asa.api.demo.services;

import com.asa.api.demo.constant.car.Category;
import com.asa.api.demo.constant.car.Energy;
import com.asa.api.demo.constant.car.Mark;
import com.asa.api.demo.exception.ResourceException;
import com.asa.api.demo.model.Car;
import com.asa.api.demo.repository.CarRepository;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;
    @Captor
    private ArgumentCaptor<String> idCaptor;
    @Captor
    private ArgumentCaptor<Car> carCaptor;

    @InjectMocks
    private CarService carService = new CarServiceImpl();

    @Test
    public void testGetCars() {
        when(carRepository.findAll()).thenReturn(Arrays.asList(getCar().get()));
        List<Car> cars = carService.getCars();
        assertTrue(!cars.isEmpty());
        verify(carRepository, times(1)).findAll();
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCarById_WithNullValue() {
        carService.getCarById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCarById_WithEmptyValue() {
        carService.getCarById("");
    }

    @Test
    public void testGetCarById() {
        Optional<Car> _car = getCar();
        when(carRepository.findById(idCaptor.capture())).thenReturn(_car);
        Car car = carService.getCarById("id");
        assertNotNull(car);
        verify(carRepository, times(1)).findById(idCaptor.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCar_withNullValue() {
        carService.addCar(null);
    }

    @Test
    public void testAddCar() {
        Car _car = getCar().get();
        when(carRepository.save(carCaptor.capture())).thenReturn(_car);
        Car car = carService.addCar(_car);
        assertNotNull(car.getId());
        assertEquals(car.getId(),_car.getId());
        assertEquals(car, _car);
        verify(carRepository, times(1)).save(carCaptor.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteCar_WithNullValue() {
        carService.deleteCar(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteCar_WithEmptyValue() {
        carService.deleteCar("");
    }

    @Test(expected = ResourceException.class)
    public void testDeleteCar_WithCarNotFound() {
        when(carRepository.findById(idCaptor.capture())).thenReturn(Optional.empty());
        carService.deleteCar("id");
        verify(carRepository, times(1)).findById(idCaptor.getValue());
    }

    @Test
    public void testDeleteCar_withCarFound() {
        when(carRepository.findById(idCaptor.capture())).thenReturn(getCar());
        doNothing().when(carRepository).delete(carCaptor.capture());
        boolean result = carService.deleteCar("id");
        assertTrue(result);
        verify(carRepository, times(1)).findById(idCaptor.getValue());
        verify(carRepository, times(1)).delete(carCaptor.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateCar_withNullValue() {
        carService.updateCar(null, getCar().get());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateCar_withEmptyValue() {
        carService.updateCar("", getCar().get());
    }

    @Test
    public void testUpdateCar() {
        Car car = getCar().get();
        car.setId("id");
        when(carRepository.findById(idCaptor.capture())).thenReturn(Optional.of(car));
        when(carRepository.save(carCaptor.capture())).thenReturn(car);
        boolean result = carService.updateCar("id", car);
        verify(carRepository, times(1)).findById(idCaptor.getValue());
        verify(carRepository, times(1)).save(carCaptor.getValue());
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