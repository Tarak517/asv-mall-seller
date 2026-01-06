package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.OrderItem;
import com.gantasoft.asvmallsellersv1apis.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository repo;

    public OrderItemService(OrderItemRepository repo) {
        this.repo = repo;
    }

    public OrderItem create(OrderItem item) {
        return repo.save(item);
    }

    public OrderItem getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<OrderItem> getAll() {
        return repo.findAll();
    }

    public List<OrderItem> getByOrderId(Long orderId) {
        return repo.findByOrderId(orderId);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
