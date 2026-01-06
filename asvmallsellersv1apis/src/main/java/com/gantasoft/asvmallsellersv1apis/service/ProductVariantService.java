package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.ProductVariant;
import com.gantasoft.asvmallsellersv1apis.repository.ProductVariantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVariantService {

    private final ProductVariantRepository repo;

    public ProductVariantService(ProductVariantRepository repo) {
        this.repo = repo;
    }

    public ProductVariant save(ProductVariant variant) {
        return repo.save(variant);
    }

    public List<ProductVariant> getAll() {
        return repo.findAll();
    }

    public ProductVariant getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
