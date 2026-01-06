package com.gantasoft.asvmallsellersv1apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gantasoft.asvmallsellersv1apis.entity.ProductOptionValue;
import java.util.List;

@Repository
public interface ProductOptionValueRepository extends JpaRepository<ProductOptionValue, Long> {
    List<ProductOptionValue> findByOptionOptionId(Long optionId);
}
