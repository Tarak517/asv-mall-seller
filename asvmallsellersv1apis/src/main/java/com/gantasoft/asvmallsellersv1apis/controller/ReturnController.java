package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.Return;
import com.gantasoft.asvmallsellersv1apis.service.ReturnService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/returns")
public class ReturnController {

    private final ReturnService service;

    public ReturnController(ReturnService service) {
        this.service = service;
    }

    @GetMapping
    public List<Return> getAllReturns() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Return getReturn(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Return createReturn(@RequestBody Return r) {
        return service.save(r);
    }

    @PutMapping("/{id}")
    public Return updateReturn(@PathVariable Long id, @RequestBody Return r) {
        Return existing = service.getById(id);
        if (existing != null) {
            existing.setOrderId(r.getOrderId());
            existing.setReason(r.getReason());
            existing.setStatus(r.getStatus());
            existing.setUpdatedAt(r.getUpdatedAt());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteReturn(@PathVariable Long id) {
        service.delete(id);
    }
}
