package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.SellerPayout;
import com.gantasoft.asvmallsellersv1apis.repository.SellerPayoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerPayoutService {

    private final SellerPayoutRepository repo;

    public SellerPayoutService(SellerPayoutRepository repo) {
        this.repo = repo;
    }

    public SellerPayout save(SellerPayout payout) {
        return repo.save(payout);
    }

    public List<SellerPayout> getAll() {
        return repo.findAll();
    }

    public SellerPayout getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
