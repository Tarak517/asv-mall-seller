package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.ProductVariant;
import com.gantasoft.asvmallsellersv1apis.service.ProductVariantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-variants")
public class ProductVariantController {

    private final ProductVariantService service;

    public ProductVariantController(ProductVariantService service) {
        this.service = service;
    }

    @PostMapping
    public ProductVariant save(@RequestBody ProductVariant variant) {
        return service.save(variant);
    }

    @GetMapping
    public List<ProductVariant> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProductVariant getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
