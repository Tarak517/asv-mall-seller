package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.Return;
import com.gantasoft.asvmallsellersv1apis.repository.ReturnRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReturnService {

    private final ReturnRepository repo;

    public ReturnService(ReturnRepository repo) {
        this.repo = repo;
    }

    public Return save(Return r) {
        return repo.save(r);
    }

    public List<Return> getAll() {
        return repo.findAll();
    }

    public Return getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
