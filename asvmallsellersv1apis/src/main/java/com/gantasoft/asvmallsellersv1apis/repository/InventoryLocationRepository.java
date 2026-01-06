package com.gantasoft.asvmallsellersv1apis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gantasoft.asvmallsellersv1apis.entity.InventoryLocation;
import com.gantasoft.asvmallsellersv1apis.entity.InventoryLocationId;

@Repository
public interface InventoryLocationRepository
        extends CrudRepository<InventoryLocation, InventoryLocationId> {
}
