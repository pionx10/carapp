package com.sog.carapp.repository;

import com.sog.carapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * customer repository for crud methods
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
