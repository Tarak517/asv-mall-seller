package com.gantasoft.asvmallsellersv1apis.controller;

import org.springframework.web.bind.annotation.*;
import com.gantasoft.asvmallsellersv1apis.entity.Customer;
import com.gantasoft.asvmallsellersv1apis.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Create customer
    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        if (customer.getPassword() != null) {
            customer.setPasswordHash(customer.getPassword());
        }
        return customerService.save(customer);
    }

    // Get all customers
    @GetMapping
    public List<Customer> getAll() {
        return customerService.findAll();
    }

    // Get customer by ID
    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id) {
        return customerService.findById(id);
    }
}
