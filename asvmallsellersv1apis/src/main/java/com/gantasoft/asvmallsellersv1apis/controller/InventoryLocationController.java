package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.InventoryLocation;
import com.gantasoft.asvmallsellersv1apis.service.InventoryLocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory-locations")
public class InventoryLocationController {

    private final InventoryLocationService service;

    public InventoryLocationController(InventoryLocationService service) {
        this.service = service;
    }

    @PostMapping
    public InventoryLocation create(@RequestBody InventoryLocation il) {
        return service.save(il);
    }

    @GetMapping
    public List<InventoryLocation> getAll() {
        return service.getAll();
    }

    @GetMapping("/{variantId}/{locationId}")
    public InventoryLocation getOne(@PathVariable Long variantId, @PathVariable Long locationId) {
        return service.getById(variantId, locationId);
    }

    @DeleteMapping("/{variantId}/{locationId}")
    public void delete(@PathVariable Long variantId, @PathVariable Long locationId) {
        service.delete(variantId, locationId);
    }
}
