package com.gantasoft.asvmallsellersv1apis.repository;

import com.gantasoft.asvmallsellersv1apis.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
