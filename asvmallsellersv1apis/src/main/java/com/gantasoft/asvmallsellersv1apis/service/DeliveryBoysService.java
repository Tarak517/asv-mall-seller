package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.DeliveryBoy;
import com.gantasoft.asvmallsellersv1apis.repository.DeliveryBoysRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryBoysService {

    private final DeliveryBoysRepository repository;

    public DeliveryBoysService(DeliveryBoysRepository repository) {
        this.repository = repository;
    }

    public DeliveryBoy save(DeliveryBoy db) {
        return repository.save(db);
    }

    public List<DeliveryBoy> findAll() {
        return repository.findAll();
    }

    public DeliveryBoy findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
