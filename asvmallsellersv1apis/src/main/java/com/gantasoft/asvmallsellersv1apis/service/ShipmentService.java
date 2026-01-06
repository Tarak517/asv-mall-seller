package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.Shipment;
import com.gantasoft.asvmallsellersv1apis.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    private final ShipmentRepository repository;

    public ShipmentService(ShipmentRepository repository) {
        this.repository = repository;
    }

    public Shipment create(Shipment shipment) {
        return repository.save(shipment);
    }

    public Shipment update(Shipment shipment) {
        return repository.save(shipment);  // ✅ save() works for both insert and update
    }

    public List<Shipment> getAll() {
        return repository.findAll();
    }

    public Shipment getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
