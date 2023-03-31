package com.sog.carapp.repository;

import com.sog.carapp.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * car repository for crud methods
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
