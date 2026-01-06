package com.gantasoft.asvmallsellersv1apis.service;

import org.springframework.stereotype.Service;
import com.gantasoft.asvmallsellersv1apis.entity.ProductOption;
import com.gantasoft.asvmallsellersv1apis.repository.ProductOptionRepository;

import java.util.List;

@Service
public class ProductOptionService {

    private final ProductOptionRepository repo;

    public ProductOptionService(ProductOptionRepository repo) {
        this.repo = repo;
    }

    public ProductOption save(ProductOption option) {
        return repo.save(option);
    }

    public List<ProductOption> getAll() {
        return repo.findAll();
    }

    public List<ProductOption> getByProductId(Long productId) {
        return repo.findByProductProductId(productId);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
