package com.sog.carapp.service;

import com.sog.carapp.entity.Customer;
import com.sog.carapp.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * customer service for backend methods
 */
@Service
@Transactional
@AllArgsConstructor
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * @return list of all customers in database
     */
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    /**
     * save customer in database
     *
     * @param customer
     * @return customer
     */
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * add customer to database
     *
     * @param customer
     * @return customer
     */
    public Customer add(Customer customer) {
        return save(customer);
    }

    /**
     * @param id
     * @return customer
     */
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    /**
     * change customer with id according to new parameters in source
     *
     * @param id
     * @param source
     * @return customer
     */
    public Customer updateById(Long id, Customer source) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer target = optionalCustomer.get();
            target.setName((source.getName() == null) ? target.getName() : source.getName());
            target.setStreet((source.getStreet() == null) ? target.getStreet() : source.getStreet());
            target.setHouseNumber((source.getHouseNumber() == 0) ? target.getHouseNumber() : source.getHouseNumber());
            target.setZipcode((source.getZipcode() == null) ? target.getZipcode() : source.getZipcode());
            target.setPlace((source.getPlace() == null) ? target.getPlace() : source.getPlace());
            target.setEmailAddress((source.getEmailAddress() == null) ? target.getEmailAddress() : source.getEmailAddress());
            target.setPhoneNumber((source.getPhoneNumber() == null) ? target.getPhoneNumber() : source.getPhoneNumber());
            return this.save(target);
        } else {
            return null; // not found
        }
    }

    /**
     * delete customer from database
     *
     * @param id
     */
    public void deleteById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            customerRepository.deleteById(id);
        }
    }

}
