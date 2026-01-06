package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.VariantOptionValue;
import com.gantasoft.asvmallsellersv1apis.entity.VariantOptionValueId;
import com.gantasoft.asvmallsellersv1apis.repository.VariantOptionValueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantOptionValuesService {

    private final VariantOptionValueRepository repository;

    public VariantOptionValuesService(VariantOptionValueRepository repository) {
        this.repository = repository;
    }

    public List<VariantOptionValue> getAll() {
        return repository.findAll();
    }

    public VariantOptionValue getById(VariantOptionValueId id) {
        return repository.findById(id).orElse(null);
    }

    public VariantOptionValue save(VariantOptionValue entity) {
        return repository.save(entity);
    }

    public void delete(VariantOptionValueId id) {
        repository.deleteById(id);
    }
}
