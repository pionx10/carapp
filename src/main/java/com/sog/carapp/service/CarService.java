package com.sog.carapp.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.sog.carapp.entity.CSVFile;
import com.sog.carapp.entity.Car;
import com.sog.carapp.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * car service for backend methods
 */
@Service
@Transactional
@AllArgsConstructor
public class CarService {
    @Autowired
    private CarRepository carRepository;

    /**
     * @return list of all cars in database
     */
    public Iterable<Car> findAll() {
        return carRepository.findAll();
    }

    /**
     * save car in database
     *
     * @param car
     * @return car
     */
    public Car save(Car car) {
        return carRepository.save(car);
    }

    /**
     * add car to database
     *
     * @param car
     * @return car
     */
    public Car add(Car car) {
        return save(car);
    }

    /**
     * @param id
     * @return car
     */
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    /**
     * change car with id according to new parameters in source
     *
     * @param id
     * @param source
     * @return car
     */
    public Car updateById(Long id, Car source) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
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
        } else {
            return null; // not found
        }
    }

    /**
     * delete car from database
     *
     * @param id
     */
    public void deleteById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            carRepository.deleteById(id);
        }
    }

    /**
     * @param id
     * @return lease rate of car
     */
    public float getLeaseRate(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            float mileage = optionalCar.get().getMileage();
            int duration = optionalCar.get().getDuration();
            float nettPrice = (optionalCar.get().getNettPrice() == 0)? 1 : optionalCar.get().getNettPrice();
            float interestRate = optionalCar.get().getInterestRate();
            return (((mileage / 12) * duration) / nettPrice) + (((interestRate / 100) * nettPrice) / 12);
        } else {
            return -1; // not found
        }
    }

    /**
     * write data from csv file to database
     *
     * @param file path
     * @return message that upload was successful
     * @throws IOException
     * @throws CsvException
     */
    public String uploadCSVFile(CSVFile file) throws IOException, CsvException {
        String path = System.getProperty("user.dir") + File.separator + file.getPath();
        CSVReader reader = new CSVReader(new FileReader(path));
        List<String[]> rows = reader.readAll();

        //TODO: rows in testdata are inconsistent and not all can be added in the same way
        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);
            List<String> values = new ArrayList<>();
            for (String column : row) {
                String[] arrSplit = column.split(";");
                for (String value : arrSplit) {
                    values.add(value);
                }
            }

            Car car = new Car();
            car.setMake(values.get(0));
            car.setModel(values.get(1));
            car.setVersion(values.get(2));
            try {
                car.setNumberOfDoors(Integer.valueOf(values.get(3)));
                car.setGrossPrice(Float.valueOf(values.get(4)));
                car.setNettPrice(Float.valueOf(values.get(5)));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            //TODO: what is hp?

            add(car);
        }

        return "file-upload-status";
    }
}
