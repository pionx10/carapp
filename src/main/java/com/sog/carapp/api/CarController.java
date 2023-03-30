package com.sog.carapp.api;

import com.opencsv.exceptions.CsvException;
import com.sog.carapp.entity.CSVFile;
import com.sog.carapp.entity.Car;
import com.sog.carapp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * @return list of all cars in database
     */
    @GetMapping
    public Iterable<Car> list() {
        return this.carService.findAll();
    }

    /**
     * @param id
     * @return car
     */
    @GetMapping("{id}")
    public Optional<Car> read(@PathVariable Long id) {
        return this.carService.findById(id);
    }

    /**
     * add car to database
     *
     * @param car
     * @return car
     */
    @PostMapping
    public Car add(@RequestBody Car car) {
        return this.carService.add(car);
    }

    /**
     * change car with id according to new parameters in source
     *
     * @param id
     * @param source
     * @return car
     */
    @PutMapping("{id}")
    public Car change(@PathVariable Long id, @RequestBody Car source) {
        return this.carService.updateById(id, source);
    }

    /**
     * delete car from database
     *
     * @param id
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.carService.deleteById(id);
    }

    /**
     * @param id
     * @return lease rate of car
     */
    @GetMapping("{id}/leaserate")
    public float leaseRate(@PathVariable Long id) {
        return this.carService.getLeaseRate(id);
    }

    /**
     * write data from csv file to database
     *
     * @param file path
     * @return message that upload was successful
     * @throws IOException
     * @throws CsvException
     */
    @PostMapping("/uploadcsv")
    public String uploadCSVFile(@RequestBody CSVFile file) throws IOException, CsvException { return  this.carService.uploadCSVFile(file); }
}
