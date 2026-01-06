package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.ReturnItem;
import com.gantasoft.asvmallsellersv1apis.repository.ReturnItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReturnItemService {

    private final ReturnItemRepository repo;

    public ReturnItemService(ReturnItemRepository repo) {
        this.repo = repo;
    }

    public ReturnItem save(ReturnItem ri) {
        return repo.save(ri);
    }

    public List<ReturnItem> getAll() {
        return repo.findAll();
    }

    public ReturnItem getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
