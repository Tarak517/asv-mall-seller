package com.gantasoft.asvmallsellersv1apis.controller;

import org.springframework.web.bind.annotation.*;
import com.gantasoft.asvmallsellersv1apis.entity.ProductOptionValue;
import com.gantasoft.asvmallsellersv1apis.service.ProductOptionValueService;

import java.util.List;

@RestController
@RequestMapping("/api/product-option-values")
public class ProductOptionValueController {

    private final ProductOptionValueService service;

    public ProductOptionValueController(ProductOptionValueService service) {
        this.service = service;
    }

    @PostMapping
    public ProductOptionValue create(@RequestBody ProductOptionValue value) {
        return service.save(value);
    }

    @GetMapping
    public List<ProductOptionValue> getAll() {
        return service.getAll();
    }

    @GetMapping("/option/{optionId}")
    public List<ProductOptionValue> getByOption(@PathVariable Long optionId) {
        return service.getByOptionId(optionId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
