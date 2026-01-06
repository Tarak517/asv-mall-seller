package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.Seller;
import com.gantasoft.asvmallsellersv1apis.service.SellerService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    private final SellerService service;

    public SellerController(SellerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Seller> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Seller get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Seller create(@RequestBody Seller seller) {
        return service.save(seller);
    }

    @PutMapping("/{id}")
    public Seller update(@PathVariable Long id, @RequestBody Seller seller) {
        Seller existing = service.getById(id);
        if (existing != null) {
            existing.setOwnerName(seller.getOwnerName());
            existing.setCompanyName(seller.getCompanyName());
            existing.setEmail(seller.getEmail());
            existing.setPhone(seller.getPhone());
            existing.setPasswordHash(seller.getPasswordHash());
            existing.setGstin(seller.getGstin());
            existing.setKycStatus(seller.getKycStatus());
            existing.setStatus(seller.getStatus());
            existing.setUpdatedAt(seller.getUpdatedAt());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
