package com.gantasoft.asvmallsellersv1apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gantasoft.asvmallsellersv1apis.entity.ProductTax;
import com.gantasoft.asvmallsellersv1apis.entity.ProductTaxId;

@Repository
public interface ProductTaxRepository extends JpaRepository<ProductTax, ProductTaxId> {
}
