package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.Addresses;
import com.gantasoft.asvmallsellersv1apis.service.AddressesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/addresses")
public class AddressesController {

    private final AddressesService service;

    public AddressesController(AddressesService service) {
        this.service = service;
    }

    @GetMapping
    public List<Addresses> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Addresses> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Addresses create(@RequestBody Addresses address) {
        return service.save(address);
    }

    @PutMapping("/{id}")
    public Addresses update(@PathVariable Long id, @RequestBody Addresses address) {
        return service.update(id, address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        if (service.getById(id).isPresent()) {
            service.delete(id);
            response.put("message", "Address deleted successfully");
            response.put("addressId", id);
            return ResponseEntity.ok(response); 
        } else {
            response.put("message", "Address not found");
            response.put("addressId", id);
            return ResponseEntity.status(404).body(response); 
        }
    }
}
