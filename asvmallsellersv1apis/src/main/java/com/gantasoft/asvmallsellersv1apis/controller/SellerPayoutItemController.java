package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.SellerPayoutItem;
import com.gantasoft.asvmallsellersv1apis.service.SellerPayoutItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller-payout-items")
public class SellerPayoutItemController {

    private final SellerPayoutItemService service;

    public SellerPayoutItemController(SellerPayoutItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<SellerPayoutItem> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SellerPayoutItem getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public SellerPayoutItem create(@RequestBody SellerPayoutItem item) {
        return service.save(item);
    }

    @PutMapping("/{id}")
    public SellerPayoutItem update(@PathVariable Long id, @RequestBody SellerPayoutItem item) {
        SellerPayoutItem existing = service.getById(id);
        if (existing != null) {
            existing.setPayoutId(item.getPayoutId());
            existing.setOrderId(item.getOrderId());
            existing.setAmount(item.getAmount());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
