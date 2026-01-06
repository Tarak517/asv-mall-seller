package com.gantasoft.asvmallsellersv1apis.repository;

import com.gantasoft.asvmallsellersv1apis.entity.ShipmentItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentItemRepository extends JpaRepository<ShipmentItem, Long> {
}
