package com.gantasoft.asvmallsellersv1apis.controller;

import org.springframework.web.bind.annotation.*;
import com.gantasoft.asvmallsellersv1apis.entity.ProductImage;
import com.gantasoft.asvmallsellersv1apis.service.ProductImageService;

import java.util.List;

@RestController
@RequestMapping("/api/product-images")
public class ProductImageController {

    private final ProductImageService service;

    public ProductImageController(ProductImageService service) {
        this.service = service;
    }

    @PostMapping
    public ProductImage create(@RequestBody ProductImage image) {
        return service.save(image);
    }

    @GetMapping
    public List<ProductImage> getAll() {
        return service.getAll();
    }

    @GetMapping("/product/{productId}")
    public List<ProductImage> getByProduct(@PathVariable Long productId) {
        return service.getByProductId(productId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
