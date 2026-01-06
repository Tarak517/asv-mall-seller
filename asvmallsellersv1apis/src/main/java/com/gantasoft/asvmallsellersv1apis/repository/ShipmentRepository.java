package com.gantasoft.asvmallsellersv1apis.repository;

import com.gantasoft.asvmallsellersv1apis.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    boolean existsByTrackingNumber(String trackingNumber);
}
