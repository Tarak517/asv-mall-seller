package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.ReturnItem;
import com.gantasoft.asvmallsellersv1apis.service.ReturnItemService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/return-items")
public class ReturnItemController {

    private final ReturnItemService service;

    public ReturnItemController(ReturnItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<ReturnItem> getAllReturnItems() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ReturnItem getReturnItem(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ReturnItem createReturnItem(@RequestBody ReturnItem ri) {
        return service.save(ri);
    }

    @PutMapping("/{id}")
    public ReturnItem updateReturnItem(@PathVariable Long id, @RequestBody ReturnItem ri) {
        ReturnItem existing = service.getById(id);
        if (existing != null) {
            existing.setReturnId(ri.getReturnId());
            existing.setOrderItemId(ri.getOrderItemId());
            existing.setQty(ri.getQty());
            existing.setUpdatedAt(ri.getUpdatedAt());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteReturnItem(@PathVariable Long id) {
        service.delete(id);
    }
}
