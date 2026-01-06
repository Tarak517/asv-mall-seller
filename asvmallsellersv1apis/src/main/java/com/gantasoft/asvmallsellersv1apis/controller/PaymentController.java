package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.Payment;
import com.gantasoft.asvmallsellersv1apis.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    // POST: Create a new payment
    @PostMapping
    public Payment create(@RequestBody Payment payment) {
        return service.save(payment);
    }

    // GET: Get a payment by ID
    @GetMapping("/{id}")
    public Payment getById(@PathVariable Long id) {
        return service.get(id);
    }

    // GET: Get all payments
    @GetMapping
    public List<Payment> getAll() {
        return service.getAll(); // We'll add this in service
    }

    // GET: Get payments by orderId
    @GetMapping("/order/{orderId}")
    public List<Payment> getByOrder(@PathVariable Long orderId) {
        return service.getByOrderId(orderId);
    }
}
