package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.WishlistItem;
import com.gantasoft.asvmallsellersv1apis.service.WishlistItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist-items")
public class WishlistItemController {

    private final WishlistItemService service;

    public WishlistItemController(WishlistItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<WishlistItem> getAll() {
        return service.getAll();
    }

    @GetMapping("/wishlist/{wishlistId}")
    public List<WishlistItem> getByWishlist(@PathVariable Long wishlistId) {
        return service.getByWishlistId(wishlistId);
    }

    @GetMapping("/{id}")
    public WishlistItem getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public WishlistItem create(@RequestBody WishlistItem item) {
        return service.save(item);
    }

    @PutMapping("/{id}")
    public WishlistItem update(@PathVariable Long id, @RequestBody WishlistItem item) {
        WishlistItem existing = service.getById(id);
        if (existing != null) {
            existing.setWishlistId(item.getWishlistId());
            existing.setProductId(item.getProductId());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
