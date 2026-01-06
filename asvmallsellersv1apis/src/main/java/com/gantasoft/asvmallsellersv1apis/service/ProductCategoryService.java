package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.*;
import com.gantasoft.asvmallsellersv1apis.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository repository;
    private final ProductRepository productRepository;
    private final CategoriesRepository categoryRepository;

    public ProductCategoryService(ProductCategoryRepository repository,
                                  ProductRepository productRepository,
                                  CategoriesRepository categoryRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // Accept ProductCategory object (from JSON)
    public ProductCategory save(ProductCategory productCategory) {
        Long productId = productCategory.getId().getProductId();
        Long categoryId = productCategory.getId().getCategoryId();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Categories category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        productCategory.setProduct(product);
        productCategory.setCategory(category);

        return repository.save(productCategory);
    }

    public List<ProductCategory> findAll() {
        return repository.findAll();
    }
    public ProductCategory findById(ProductCategoryId id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductCategory not found"));
    }

}
