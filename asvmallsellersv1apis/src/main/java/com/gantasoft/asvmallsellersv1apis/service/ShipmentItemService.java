package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.ShipmentItem;
import com.gantasoft.asvmallsellersv1apis.repository.ShipmentItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShipmentItemService {

    private final ShipmentItemRepository repository;

    public ShipmentItemService(ShipmentItemRepository repository) {
        this.repository = repository;
    }

    public ShipmentItem save(ShipmentItem item) {
        return repository.save(item);
    }

    public List<ShipmentItem> getAll() {
        return repository.findAll();
    }

    public ShipmentItem getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
