package com.sog.carapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * car entity
 */
@Entity
@Data
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    private String make;
    private String model;
    private String version;
    private int numberOfDoors;
    private float co2Emission;
    private float grossPrice;
    private float nettPrice;
    private float mileage;
    private int duration;
    private float interestRate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date startDate;
}
