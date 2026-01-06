package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.Wishlist;
import com.gantasoft.asvmallsellersv1apis.service.WishlistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlists")
public class WishlistController {

    private final WishlistService service;

    public WishlistController(WishlistService service) {
        this.service = service;
    }

    @GetMapping
    public List<Wishlist> getAll() {
        return service.getAll();
    }

    @GetMapping("/customer/{customerId}")
    public List<Wishlist> getByCustomer(@PathVariable Long customerId) {
        return service.getByCustomerId(customerId);
    }

    @GetMapping("/{id}")
    public Wishlist getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Wishlist create(@RequestBody Wishlist wishlist) {
        return service.save(wishlist);
    }

    @PutMapping("/{id}")
    public Wishlist update(@PathVariable Long id, @RequestBody Wishlist wishlist) {
        Wishlist existing = service.getById(id);
        if (existing != null) {
            existing.setCustomerId(wishlist.getCustomerId());
            existing.setName(wishlist.getName());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
