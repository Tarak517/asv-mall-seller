package com.gantasoft.asvmallsellersv1apis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gantasoft.asvmallsellersv1apis.entity.CartItems;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems, Long> {

    List<CartItems> findByCartCartId(Long cartId);

    void deleteByCartCartId(Long cartId);
}
