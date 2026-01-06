package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.ShipmentItem;
import com.gantasoft.asvmallsellersv1apis.service.ShipmentItemService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/shipment-items")
public class ShipmentItemController {

    private final ShipmentItemService service;

    public ShipmentItemController(ShipmentItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<ShipmentItem> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ShipmentItem getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ShipmentItem create(@RequestBody ShipmentItem item) {
        return service.save(item);
    }

    @PutMapping("/{id}")
    public ShipmentItem update(@PathVariable Long id, @RequestBody ShipmentItem item) {
        ShipmentItem existing = service.getById(id);
        if (existing != null) {
            existing.setShipmentId(item.getShipmentId());
            existing.setOrderItemId(item.getOrderItemId());
            existing.setQty(item.getQty());
            existing.setUpdatedAt(item.getUpdatedAt());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
