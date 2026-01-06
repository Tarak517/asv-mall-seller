package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.Customer;
import com.gantasoft.asvmallsellersv1apis.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    // ✅ Create / Update Customer
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    // ✅ Get all customers
    public List<Customer> findAll() {
        return repository.findAll();
    }

    // ✅ Get customer by ID
    public Customer findById(Long customerId) {
        return repository.findById(customerId).orElse(null);
    }

    // ✅ Delete customer
    public void delete(Long customerId) {
        repository.deleteById(customerId);
    }
}
