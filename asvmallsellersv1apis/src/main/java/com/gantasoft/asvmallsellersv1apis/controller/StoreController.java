package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.Store;
import com.gantasoft.asvmallsellersv1apis.service.StoreService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService service;

    public StoreController(StoreService service) {
        this.service = service;
    }
    

    @GetMapping
    public List<Store> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Store getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Store create(@RequestBody Store store) {
        // Set sellerId here, for example from logged-in user or default value
        store.setSellerId(1L); // replace 1L with actual sellerId logic
        return service.save(store);
    }

    @PutMapping("/{id}")
    public Store update(@PathVariable Long id, @RequestBody Store store) {
        Store existing = service.getById(id);
        if (existing != null) {
            existing.setName(store.getName());
            existing.setSlug(store.getSlug());
            existing.setEmail(store.getEmail());
            existing.setPhone(store.getPhone());
            existing.setStatus(store.getStatus());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
