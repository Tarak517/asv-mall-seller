package com.gantasoft.asvmallsellersv1apis.controller;

import org.springframework.web.bind.annotation.*;

import com.gantasoft.asvmallsellersv1apis.entity.ProductOption;

import com.gantasoft.asvmallsellersv1apis.service.ProductOptionService;


import java.util.List;

@RestController
@RequestMapping("/api/product-options")
public class ProductOptionController {

    private final ProductOptionService service;

    public ProductOptionController(ProductOptionService service) {
        this.service = service;
    }

    @PostMapping
    public ProductOption create(@RequestBody ProductOption option) {
        return service.save(option);
    }

    @GetMapping
    public List<ProductOption> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
