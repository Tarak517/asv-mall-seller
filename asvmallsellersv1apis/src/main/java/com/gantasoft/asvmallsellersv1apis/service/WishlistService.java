package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.Wishlist;
import com.gantasoft.asvmallsellersv1apis.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository repository;

    public WishlistService(WishlistRepository repository) {
        this.repository = repository;
    }

    public List<Wishlist> getAll() {
        return repository.findAll();
    }

    public List<Wishlist> getByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    public Wishlist getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Wishlist save(Wishlist wishlist) {
        return repository.save(wishlist);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
