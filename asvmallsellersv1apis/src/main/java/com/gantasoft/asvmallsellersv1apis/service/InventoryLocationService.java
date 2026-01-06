package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.InventoryLocation;
import com.gantasoft.asvmallsellersv1apis.entity.InventoryLocationId;
import com.gantasoft.asvmallsellersv1apis.repository.InventoryLocationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventoryLocationService {

    private final InventoryLocationRepository repo;

    public InventoryLocationService(InventoryLocationRepository repo) {
        this.repo = repo;
    }

    public InventoryLocation save(InventoryLocation il) {
        return repo.save(il);
    }

    public List<InventoryLocation> getAll() {
        return (List<InventoryLocation>) repo.findAll();
    }

    public InventoryLocation getById(Long variantId, Long locationId) {
        InventoryLocationId id = new InventoryLocationId(variantId, locationId);
        return repo.findById(id).orElse(null);
    }

    public void delete(Long variantId, Long locationId) {
        repo.deleteById(new InventoryLocationId(variantId, locationId));
    }
}
