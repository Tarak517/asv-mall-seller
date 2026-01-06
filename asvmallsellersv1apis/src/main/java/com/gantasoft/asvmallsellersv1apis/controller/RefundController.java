package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.Refund;
import com.gantasoft.asvmallsellersv1apis.service.RefundService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/refunds")
public class RefundController {

    private final RefundService service;

    public RefundController(RefundService service) {
        this.service = service;
    }

    @GetMapping
    public List<Refund> getAllRefunds() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Refund getRefund(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Refund createRefund(@RequestBody Refund refund) {
        return service.save(refund);
    }

    @PutMapping("/{id}")
    public Refund updateRefund(@PathVariable Long id, @RequestBody Refund refund) {
        Refund existing = service.getById(id);
        if (existing != null) {
            existing.setPaymentId(refund.getPaymentId());
            existing.setAmount(refund.getAmount());
            existing.setReason(refund.getReason());
            existing.setStatus(refund.getStatus());
            existing.setRefundedAt(refund.getRefundedAt());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteRefund(@PathVariable Long id) {
        service.delete(id);
    }
}
