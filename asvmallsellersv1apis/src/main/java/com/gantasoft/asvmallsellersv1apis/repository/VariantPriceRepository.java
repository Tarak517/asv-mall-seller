package com.gantasoft.asvmallsellersv1apis.repository;

import com.gantasoft.asvmallsellersv1apis.entity.VariantPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantPriceRepository extends JpaRepository<VariantPrice, Long> {
    List<VariantPrice> findByProductId(Long variantId);
}
