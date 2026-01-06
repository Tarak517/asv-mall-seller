package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.Refund;
import com.gantasoft.asvmallsellersv1apis.repository.RefundRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefundService {

    private final RefundRepository repo;

    public RefundService(RefundRepository repo) {
        this.repo = repo;
    }

    public Refund save(Refund refund) {
        return repo.save(refund);
    }

    public List<Refund> getAll() {
        return repo.findAll();
    }

    public Refund getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
