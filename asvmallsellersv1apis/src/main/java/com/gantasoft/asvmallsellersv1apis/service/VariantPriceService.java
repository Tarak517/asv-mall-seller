package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.VariantPrice;
import com.gantasoft.asvmallsellersv1apis.repository.VariantPriceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantPriceService {

    private final VariantPriceRepository repository;

    public VariantPriceService(VariantPriceRepository repository) {
        this.repository = repository;
    }

    public List<VariantPrice> getAll() {
        return repository.findAll();
    }

    public List<VariantPrice> getByProductId(Long productId) {
        return repository.findByProductId(productId);
    }


    public VariantPrice getById(Long priceId) {
        return repository.findById(priceId).orElse(null);
    }

    public VariantPrice save(VariantPrice price) {
        return repository.save(price);
    }

    public void delete(Long priceId) {
        repository.deleteById(priceId);
    }
}
