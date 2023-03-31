package com.sog.carapp.service;

import com.sog.carapp.entity.Car;
import com.sog.carapp.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * unit tests for car service
 */
@ExtendWith(MockitoExtension.class)
class CarServiceTest {
    CarService carService;
    @Mock
    CarRepository carRepository;
    Car car;

    /**
     * settings before each test
     */
    @BeforeEach
    void setUp() {
        carService = new CarService(carRepository);
        car = new Car();
        car.setMake("maketest");
    }

    /**
     *
     */
    @Test
    void findAll() {
    }

    /**
     *
     */
    @Test
    void save() {
    }

    /**
     *
     */
    @Test
    void add() {
    }

    /**
     *
     */
    @Test
    void findById() {
    }

    /**
     * test update method
     */
    @Test
    void updateById() {
        Car car1 = new Car();
        car1.setMake("123test");
        when(carRepository.findById(any())).thenReturn(Optional.of(car));
        when(carRepository.save(any())).thenReturn(car);
        car1 = carService.updateById(any(), car);
        assertEquals(car1, car);
    }

    /**
     * test delete method
     */
    @Test
    void deleteById() {
        when(carRepository.findById(any())).thenReturn(Optional.of(car));
        carService.deleteById(any());
        verify(carRepository, times(1)).deleteById(any());
    }

    /**
     * test get lease rate method
     */
    @Test
    void getLeaseRate() {
        when(carRepository.findById(any())).thenReturn(Optional.of(car));
        this.car.setMileage(100);
        this.car.setDuration(222);
        this.car.setNettPrice(1234);
        this.car.setInterestRate(987);
        Float rate = (((this.car.getMileage() / 12) * this.car.getDuration()) /
                this.car.getNettPrice())+(((this.car.getInterestRate() / 100 ) *
                this.car.getNettPrice()) /12);
        assertEquals(rate, carService.getLeaseRate(any()));
    }
}