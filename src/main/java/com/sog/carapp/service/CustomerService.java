package com.sog.carapp.service;

import com.sog.carapp.entity.Customer;
import com.sog.carapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer add(Customer customer) {
        return save(customer);
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer updateById(Long id, Customer source) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()) {
            Customer target = optionalCustomer.get();
            target.setName((source.getName() == null) ? target.getName() : source.getName());
            target.setStreet((source.getStreet() == null) ? target.getStreet() : source.getStreet());
            target.setHouseNumber((source.getHouseNumber() == 0) ? target.getHouseNumber() : source.getHouseNumber());
            target.setZipcode((source.getZipcode() == null) ? target.getZipcode() : source.getZipcode());
            target.setPlace((source.getPlace() == null) ? target.getPlace() : source.getPlace());
            target.setEmailAddress((source.getEmailAddress() == null) ? target.getEmailAddress() : source.getEmailAddress());
            target.setPhoneNumber((source.getPhoneNumber() == null) ? target.getPhoneNumber() : source.getPhoneNumber());
            return this.save(target);
        }
        else {
            return null; // not found
        }
    }

    public void deleteById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()) {
            customerRepository.deleteById(id);
        }
    }

}
