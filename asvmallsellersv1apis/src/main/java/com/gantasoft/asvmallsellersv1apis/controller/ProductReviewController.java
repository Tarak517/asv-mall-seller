package com.gantasoft.asvmallsellersv1apis.controller;

import org.springframework.web.bind.annotation.*;
import com.gantasoft.asvmallsellersv1apis.entity.ProductReview;
import com.gantasoft.asvmallsellersv1apis.service.ProductReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/product-reviews")
public class ProductReviewController {

    private final ProductReviewService service;

    public ProductReviewController(ProductReviewService service) {
        this.service = service;
    }

    @PostMapping
    public ProductReview create(@RequestBody ProductReview review) {
        return service.save(review);
    }

    @GetMapping
    public List<ProductReview> getAll() {
        return service.getAll();
    }

    @GetMapping("/product/{productId}")
    public List<ProductReview> getByProduct(@PathVariable Long productId) {
        return service.getByProductId(productId);
    }

    @GetMapping("/customer/{customerId}")
    public List<ProductReview> getByCustomer(@PathVariable Long customerId) {
        return service.getByCustomerId(customerId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    @GetMapping("/{id}")
    public ProductReview getById(@PathVariable Long id) {
        return service.getById(id);
    }

}
