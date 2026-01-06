package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.StoreAddress;
import com.gantasoft.asvmallsellersv1apis.entity.StoreAddressId;
import com.gantasoft.asvmallsellersv1apis.repository.StoreAddressRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StoreAddressService {

    private final StoreAddressRepository repository;

    public StoreAddressService(StoreAddressRepository repository) {
        this.repository = repository;
    }

    public StoreAddress save(StoreAddress storeAddress) {
        return repository.save(storeAddress);
    }

    public List<StoreAddress> getAll() {
        return repository.findAll();
    }

    public StoreAddress getById(StoreAddressId id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(StoreAddressId id) {
        repository.deleteById(id);
    }
}
