package com.sog.carapp.service;

import com.sog.carapp.entity.Customer;
import com.sog.carapp.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * unit tests for customer service
 */
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    CustomerService customerService;
    @Mock
    CustomerRepository customerRepository;
    Customer customer;

    /**
     * settings before each test
     */
    @BeforeEach
    void setUp() {
        customerService = new CustomerService(customerRepository);
        customer = new Customer();
        customer.setName("tester");
    }

    /**
     * test find all method
     */
    @Test
    void findAll() {
        List<Customer> list1 = new ArrayList<>();
        list1.add(customer);
        when(customerRepository.findAll()).thenReturn(list1);
        assertEquals(list1, customerService.findAll());
    }

    /**
     * test save to database method
     */
    @Test
    void save() {
        when(customerRepository.save(any())).thenReturn(customer);

        // When
        Customer saved = customerService.save(customer);

        // Then
        verify(customerRepository).save(customer);

        assertEquals(customer, saved);
    }

    /**
     * test add to database
     */
    @Test
    void add() {
        customerService.add(this.customer);
        verify(customerRepository, times(1)).save(any());
    }

    /**
     * test find by id
     */
    @Test
    void findById() {
        when(customerRepository.findById(any())).thenReturn(Optional.ofNullable(customer));
        Optional<Customer> customer1 = customerService.findById(any());
        assertEquals(customer1.get(), customer);
    }

    /**
     *
     */
    @Test
    void updateById() {
    }

    /**
     *
     */
    @Test
    void deleteById() {
    }
}