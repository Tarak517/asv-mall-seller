package com.gantasoft.asvmallsellersv1apis.repository;

import com.gantasoft.asvmallsellersv1apis.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findByProductProductId(Long productId);

}