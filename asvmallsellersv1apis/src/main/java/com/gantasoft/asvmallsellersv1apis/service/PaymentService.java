package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.Payment;
import com.gantasoft.asvmallsellersv1apis.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository repo;

    public PaymentService(PaymentRepository repo) {
        this.repo = repo;
    }

    public Payment save(Payment payment) {
        return repo.save(payment);
    }

    public Payment get(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Payment> getAll() {
        return repo.findAll();
    }

    public List<Payment> getByOrderId(Long orderId) {
        return repo.findByOrderId(orderId);
    }

    public Payment getByProviderPaymentId(String providerPaymentId) {
        return repo.findByProviderPaymentId(providerPaymentId);
    }
}
