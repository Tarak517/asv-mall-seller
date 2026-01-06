package com.gantasoft.asvmallsellersv1apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gantasoft.asvmallsellersv1apis.entity.*;

public interface ProductCategoryRepository
        extends JpaRepository<ProductCategory, ProductCategoryId> {

}
