package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.SellerPayoutItem;
import com.gantasoft.asvmallsellersv1apis.repository.SellerPayoutItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerPayoutItemService {

    private final SellerPayoutItemRepository repo;

    public SellerPayoutItemService(SellerPayoutItemRepository repo) {
        this.repo = repo;
    }

    public SellerPayoutItem save(SellerPayoutItem item) {
        return repo.save(item);
    }

    public List<SellerPayoutItem> getAll() {
        return repo.findAll();
    }

    public SellerPayoutItem getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
