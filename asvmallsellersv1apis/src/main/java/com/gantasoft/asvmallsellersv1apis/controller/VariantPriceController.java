 package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.VariantPrice;
import com.gantasoft.asvmallsellersv1apis.service.VariantPriceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/variant-prices")
public class VariantPriceController {

    private final VariantPriceService service;

    public VariantPriceController(VariantPriceService service) {
        this.service = service;
    }

    @GetMapping
    public List<VariantPrice> getAll() {
        return service.getAll();
    }

    @GetMapping("/product/{productId}")
    public List<VariantPrice> getByProductId(@PathVariable Long productId) {
        return service.getByProductId(productId);
    }

    @GetMapping("/{priceId}")
    public VariantPrice getById(@PathVariable Long priceId) {
        return service.getById(priceId);
    }

    @PostMapping
    public VariantPrice create(@RequestBody VariantPrice price) {
        return service.save(price);
    }

    @PutMapping("/{priceId}")
    public VariantPrice update(@PathVariable Long priceId, @RequestBody VariantPrice price) {
        VariantPrice existing = service.getById(priceId);
        if (existing != null) {
            existing.setProductId(price.getProductId());
            existing.setCurrency(price.getCurrency());
            existing.setMrp(price.getMrp());
            existing.setSalePrice(price.getSalePrice());
            existing.setStartAt(price.getStartAt());
            existing.setEndAt(price.getEndAt());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{priceId}")
    public void delete(@PathVariable Long priceId) {
        service.delete(priceId);
    }
}