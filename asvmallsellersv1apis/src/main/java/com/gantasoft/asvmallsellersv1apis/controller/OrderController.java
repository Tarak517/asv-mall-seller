package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.Orders;
import com.gantasoft.asvmallsellersv1apis.enums.OrderStatus;
import com.gantasoft.asvmallsellersv1apis.service.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Orders create(@RequestBody Orders order) {
        if (order.getStatus() == null) {
            order.setStatus(OrderStatus.PLACED);
        }
        return orderService.save(order);
    }

    @GetMapping
    public List<Orders> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Orders getOrder(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        orderService.delete(id);
        return "Deleted";
    }
}
