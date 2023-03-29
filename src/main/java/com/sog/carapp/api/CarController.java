package com.sog.carapp.api;

import com.sog.carapp.entity.Car;
import com.sog.carapp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping
    public Iterable<Car> list() {
        return this.carService.findAll();
    }

    @GetMapping("{id}")
    public Optional<Car> read(@PathVariable Long id) {
        return this.carService.findById(id);
    }

    @PostMapping
    public Car add(@RequestBody Car car) {
        return this.carService.add(car);
    }

    @PutMapping("{id}")
    public Car change(@PathVariable Long id, @RequestBody Car source) {
        return this.carService.updateById(id, source);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.carService.deleteById(id);
    }

    @GetMapping("{id}/leaserate")
    public float leaseRate(@PathVariable Long id) {
        return this.carService.getLeaseRate(id);
    }
}
