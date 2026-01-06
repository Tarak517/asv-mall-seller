package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.OrderItem;
import com.gantasoft.asvmallsellersv1apis.service.OrderItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService service;

    public OrderItemController(OrderItemService service) {
        this.service = service;
    }

    
    @PostMapping
    public OrderItem create(@RequestBody OrderItem item) {
        return service.create(item);
    }

   
    @GetMapping
    public List<OrderItem> getAll() {
        return service.getAll();
    }

    
    @GetMapping("/{id}")
    public OrderItem getById(@PathVariable Long id) {
        return service.getById(id);
    }

   
    @GetMapping("/order/{orderId}")
    public List<OrderItem> getByOrder(@PathVariable Long orderId) {
        return service.getByOrderId(orderId);
    }

    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted Successfully";
    }
}
