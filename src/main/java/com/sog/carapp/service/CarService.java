package com.sog.carapp.service;

import com.sog.carapp.entity.Car;
import com.sog.carapp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public Iterable<Car> findAll() {
        return carRepository.findAll();
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public Car add(Car car) {
        return save(car);
    }

    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    public Car updateById(Long id, Car source) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if(optionalCar.isPresent()) {
            Car target = optionalCar.get();
            target.setMake((source.getMake() == null) ? target.getMake() : source.getMake());
            target.setModel((source.getModel() == null) ? target.getModel() : source.getModel());
            target.setVersion((source.getVersion() == null) ? target.getVersion() : source.getVersion());
            target.setNumberOfDoors((source.getNumberOfDoors() == 0) ? target.getNumberOfDoors() : source.getNumberOfDoors());
            target.setCo2Emission((source.getCo2Emission() == 0) ? target.getCo2Emission() : source.getCo2Emission());
            target.setGrossPrice((source.getGrossPrice() == 0) ? target.getGrossPrice() : source.getGrossPrice());
            target.setNettPrice((source.getNettPrice() == 0) ? target.getNettPrice() : source.getNettPrice());
            target.setMileage((source.getMileage() == 0) ? target.getMileage() : source.getMileage());
            target.setDuration((source.getDuration() == 0) ? target.getDuration() : source.getDuration());
            target.setInterestRate((source.getInterestRate() == 0) ? target.getInterestRate() : source.getInterestRate());
            target.setStartDate((source.getStartDate() == null) ? target.getStartDate() : source.getStartDate());
            return this.save(target);
        }
        else {
            return null; // not found
        }
    }

    public void deleteById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if(optionalCar.isPresent()) {
            carRepository.deleteById(id);
        }
    }

    public float getLeaseRate(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if(optionalCar.isPresent()) {
            return (((optionalCar.get().getMileage() / 12) * optionalCar.get().getDuration()) /
                    optionalCar.get().getNettPrice())+(((optionalCar.get().getInterestRate() / 100 ) *
                    optionalCar.get().getNettPrice()) /12);
        }
        else {
            return -1; // not found
        }
    }
}
