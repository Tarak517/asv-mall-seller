package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.Seller;
import com.gantasoft.asvmallsellersv1apis.repository.SellerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SellerService {

    private final SellerRepository repo;

    public SellerService(SellerRepository repo) {
        this.repo = repo;
    }

    public Seller save(Seller seller) {
        return repo.save(seller);
    }

    public List<Seller> getAll() {
        return repo.findAll();
    }

    public Seller getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
