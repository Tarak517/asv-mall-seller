package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.DeliveryBoy;
import com.gantasoft.asvmallsellersv1apis.service.DeliveryBoysService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery-boys")
public class DeliveryBoysController {

    private final DeliveryBoysService service;

    public DeliveryBoysController(DeliveryBoysService service) {
        this.service = service;
    }

    @PostMapping
    public DeliveryBoy create(@RequestBody DeliveryBoy db) {
        return service.save(db);
    }

    @GetMapping
    public List<DeliveryBoy> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public DeliveryBoy getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
