package com.sog.carapp.api;

import com.sog.carapp.entity.Customer;
import com.sog.carapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Iterable<Customer> list() {
        return this.customerService.findAll();
    }

    @GetMapping("{id}")
    public Optional<Customer> read(@PathVariable Long id) {
        return this.customerService.findById(id);
    }

    @PostMapping
    public Customer add(@RequestBody Customer customer) {
        return this.customerService.add(customer);
    }

    @PutMapping("{id}")
    public Customer change(@PathVariable Long id, @RequestBody Customer source) {
        return this.customerService.updateById(id, source);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.customerService.deleteById(id);
    }
}
