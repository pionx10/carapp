package com.sog.carapp.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String street;
    private int houseNumber;
    private String zipcode;
    private String place;
    private String emailAddress;
    private String phoneNumber;
}
