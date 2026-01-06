package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.CustomerAddress;
import com.gantasoft.asvmallsellersv1apis.service.CustomerAddressService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer-addresses")
public class CustomerAddressController {

    private final CustomerAddressService service;

    public CustomerAddressController(CustomerAddressService service) {
        this.service = service;
    }

    // ✅ GET ALL
    @GetMapping
    public List<CustomerAddress> getAll() {
        return service.getAll();
    }

    // ✅ GET BY CUSTOMER ID
    @GetMapping("/customer/{customerId}")
    public List<CustomerAddress> getByCustomer(@PathVariable Long customerId) {
        return service.getByCustomerId(customerId);
    }

    // ✅ CREATE
    @PostMapping
    public CustomerAddress create(@RequestBody CustomerAddress customerAddress) {
        return service.save(customerAddress);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
