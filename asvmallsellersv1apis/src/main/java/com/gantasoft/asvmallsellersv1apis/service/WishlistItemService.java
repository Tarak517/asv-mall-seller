package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.WishlistItem;
import com.gantasoft.asvmallsellersv1apis.repository.WishListItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistItemService {

    private final WishListItemRepository repository;

    public WishlistItemService(WishListItemRepository repository) {
        this.repository = repository;
    }

    public List<WishlistItem> getAll() {
        return repository.findAll();
    }

    public List<WishlistItem> getByWishlistId(Long wishlistId) {
        return repository.findByWishlistId(wishlistId);
    }

    public WishlistItem getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public WishlistItem save(WishlistItem item) {
        return repository.save(item);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
