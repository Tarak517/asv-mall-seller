package com.gantasoft.asvmallsellersv1apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gantasoft.asvmallsellersv1apis.entity.ProductOption;
import java.util.List;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
    List<ProductOption> findByProductProductId(Long productId);
}
