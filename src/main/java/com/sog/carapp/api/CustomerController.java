package com.sog.carapp.api;

import com.sog.carapp.entity.Customer;
import com.sog.carapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * customer rest controller for getting requests
 */
@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * @return list of all customers in database
     */
    @GetMapping
    public Iterable<Customer> list() {
        return this.customerService.findAll();
    }

    /**
     * @param id
     * @return customer
     */
    @GetMapping("{id}")
    public Optional<Customer> read(@PathVariable Long id) {
        return this.customerService.findById(id);
    }

    /**
     * add customer to database
     *
     * @param customer
     * @return customer
     */
    @PostMapping
    public Customer add(@RequestBody Customer customer) {
        return this.customerService.add(customer);
    }

    /**
     * change customer with id according to new parameters in source
     *
     * @param id
     * @param source
     * @return customer
     */
    @PutMapping("{id}")
    public Customer change(@PathVariable Long id, @RequestBody Customer source) {
        return this.customerService.updateById(id, source);
    }

    /**
     * delete customer from database
     *
     * @param id
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.customerService.deleteById(id);
    }
}
