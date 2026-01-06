package com.gantasoft.asvmallsellersv1apis.service;

import org.springframework.stereotype.Service;
import com.gantasoft.asvmallsellersv1apis.entity.ProductReview;
import com.gantasoft.asvmallsellersv1apis.repository.ProductReviewRepository;

import java.util.List;

@Service
public class ProductReviewService {

    private final ProductReviewRepository repo;

    public ProductReviewService(ProductReviewRepository repo) {
        this.repo = repo;
    }

    public ProductReview save(ProductReview review) {
        return repo.save(review);
    }

    public List<ProductReview> getAll() {
        return repo.findAll();
    }

    public List<ProductReview> getByProductId(Long productId) {
        return repo.findByProductProductId(productId);
    }
    public ProductReview getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<ProductReview> getByCustomerId(Long customerId) {
        return repo.findByCustomerCustomerId(customerId);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
   

}
