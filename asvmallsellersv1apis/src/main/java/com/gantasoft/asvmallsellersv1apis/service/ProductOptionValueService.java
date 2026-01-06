package com.gantasoft.asvmallsellersv1apis.service;

import org.springframework.stereotype.Service;
import com.gantasoft.asvmallsellersv1apis.entity.ProductOptionValue;
import com.gantasoft.asvmallsellersv1apis.repository.ProductOptionValueRepository;

import java.util.List;

@Service
public class ProductOptionValueService {

    private final ProductOptionValueRepository repo;

    public ProductOptionValueService(ProductOptionValueRepository repo) {
        this.repo = repo;
    }

    public ProductOptionValue save(ProductOptionValue value) {
        return repo.save(value);
    }

    public List<ProductOptionValue> getAll() {
        return repo.findAll();
    }

    public List<ProductOptionValue> getByOptionId(Long optionId) {
        return repo.findByOptionOptionId(optionId);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
