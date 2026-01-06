package com.gantasoft.asvmallsellersv1apis.repository;

import com.gantasoft.asvmallsellersv1apis.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
}
