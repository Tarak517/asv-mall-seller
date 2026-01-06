package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.ProductCategory;
import com.gantasoft.asvmallsellersv1apis.entity.ProductCategoryId;
import com.gantasoft.asvmallsellersv1apis.service.ProductCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-categories")
public class ProductCategoryController {

    private final ProductCategoryService service;

    public ProductCategoryController(ProductCategoryService service) {
        this.service = service;
    }

    // ✅ CREATE
    @PostMapping
    public ProductCategory create(@RequestBody ProductCategory productCategory) {
        return service.save(productCategory);
    }

    // ✅ GET ALL
    @GetMapping
    public List<ProductCategory> getAll() {
        return service.findAll();
    }

    // ✅ GET BY ID (composite key)
    @GetMapping("/{productId}/{categoryId}")
    public ProductCategory getById(
            @PathVariable Long productId,
            @PathVariable Long categoryId) {

        ProductCategoryId id = new ProductCategoryId(productId, categoryId);
        return service.findById(id);
    }
}
