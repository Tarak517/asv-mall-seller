package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.Inventory;
import com.gantasoft.asvmallsellersv1apis.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public Inventory save(Inventory inventory) {
        return repository.save(inventory);
    }

    public List<Inventory> findAll() {
        return repository.findAll();
    }

    public Inventory findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Inventory findByVariantAndLocation(Long variantId, Long locationId) {
        return repository.findByVariantIdAndLocationId(variantId, locationId);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
