package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.StockMovement;
import com.gantasoft.asvmallsellersv1apis.service.StockMovementService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/stock-movements")
public class StockMovementController {

    private final StockMovementService service;

    public StockMovementController(StockMovementService service) {
        this.service = service;
    }

    @GetMapping
    public List<StockMovement> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public StockMovement getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public StockMovement create(@RequestBody StockMovement movement) {
        return service.save(movement);
    }

    @PutMapping("/{id}")
    public StockMovement update(@PathVariable Long id, @RequestBody StockMovement movement) {
        StockMovement existing = service.getById(id);
        if (existing != null) {
            existing.setVariantId(movement.getVariantId());
            existing.setLocationId(movement.getLocationId());
            existing.setType(movement.getType());
            existing.setQuantity(movement.getQuantity());
            existing.setReason(movement.getReason());
            existing.setMovedAt(movement.getMovedAt());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
