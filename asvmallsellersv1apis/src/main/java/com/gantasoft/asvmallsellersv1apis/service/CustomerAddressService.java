package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.CustomerAddress;
import com.gantasoft.asvmallsellersv1apis.repository.CustomerAddressRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerAddressService {

    private final CustomerAddressRepository repository;

    public CustomerAddressService(CustomerAddressRepository repository) {
        this.repository = repository;
    }

    public CustomerAddress save(CustomerAddress customerAddress) {
        return repository.save(customerAddress);
    }

    public List<CustomerAddress> getByCustomerId(Long customerId) {
        return repository.findByCustomerCustomerId(customerId);
    }

    public List<CustomerAddress> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
