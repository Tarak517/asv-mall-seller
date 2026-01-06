package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.Taxes;
import com.gantasoft.asvmallsellersv1apis.repository.TaxesRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaxesService {

    private final TaxesRepository repository;

    public TaxesService(TaxesRepository repository) {
        this.repository = repository;
    }

    public List<Taxes> getAll() {
        return repository.findAll();
    }

    public Taxes getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Taxes save(Taxes tax) {
        return repository.save(tax);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
