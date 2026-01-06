package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.ProductTax;
import com.gantasoft.asvmallsellersv1apis.service.ProductTaxService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-taxes")
public class ProductTaxController {

    private final ProductTaxService service;

    public ProductTaxController(ProductTaxService service) {
        this.service = service;
    }

    @PostMapping
    public ProductTax save(@RequestBody ProductTax pt) {
        return service.save(pt);
    }

    @GetMapping
    public List<ProductTax> getAll() {
        return service.getAll();
    }

    @GetMapping("/product/{productId}/tax/{taxId}")
    public ProductTax getById(@PathVariable Long productId, @PathVariable Long taxId) {
        return service.getById(productId, taxId);
    }

    @DeleteMapping("/product/{productId}/tax/{taxId}")
    public void delete(@PathVariable Long productId, @PathVariable Long taxId) {
        service.delete(productId, taxId);
    }
}
