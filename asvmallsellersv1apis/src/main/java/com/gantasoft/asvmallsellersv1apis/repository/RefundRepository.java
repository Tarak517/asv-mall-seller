package com.gantasoft.asvmallsellersv1apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gantasoft.asvmallsellersv1apis.entity.Refund;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {
    // You can add custom queries here if needed
}
