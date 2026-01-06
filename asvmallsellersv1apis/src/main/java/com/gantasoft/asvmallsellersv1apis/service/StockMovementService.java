package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.StockMovement;
import com.gantasoft.asvmallsellersv1apis.repository.StockMovementRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StockMovementService {

    private final StockMovementRepository repository;

    public StockMovementService(StockMovementRepository repository) {
        this.repository = repository;
    }

    public StockMovement save(StockMovement movement) {
        return repository.save(movement);
    }

    public List<StockMovement> getAll() {
        return repository.findAll();
    }

    public StockMovement getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
