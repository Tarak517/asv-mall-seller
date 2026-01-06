package com.gantasoft.asvmallsellersv1apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gantasoft.asvmallsellersv1apis.entity.VariantOptionValue;
import com.gantasoft.asvmallsellersv1apis.entity.VariantOptionValueId;

public interface VariantOptionValueRepository
        extends JpaRepository<VariantOptionValue, VariantOptionValueId> {
}

