package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.Shipment;
import com.gantasoft.asvmallsellersv1apis.service.ShipmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    private final ShipmentService service;

    public ShipmentController(ShipmentService service) {
        this.service = service;
    }

   
    @PostMapping
    public Shipment createShipment(@RequestBody Shipment shipment) {
        return service.create(shipment);
    }

    // ✅ READ ALL
    @GetMapping
    public List<Shipment> getAllShipments() {
        return service.getAll();
    }

  
    @GetMapping("/{id}")
    public Shipment getShipmentById(@PathVariable Long id) {
        Shipment shipment = service.getById(id);
        if (shipment == null) {
            throw new RuntimeException("Shipment not found with id " + id);
        }
        return shipment;
    }

    
    @PutMapping("/{id}")
    public Shipment updateShipment(
            @PathVariable Long id,
            @RequestBody Shipment shipment) {

        Shipment existing = service.getById(id);
        if (existing == null) {
            throw new RuntimeException("Shipment not found with id " + id);
        }

        existing.setTrackingNumber(shipment.getTrackingNumber());
        existing.setCarrier(shipment.getCarrier());
        existing.setStatus(shipment.getStatus());
        existing.setShippedAt(shipment.getShippedAt());
        existing.setDeliveredAt(shipment.getDeliveredAt());

        return service.update(existing);
    }

    
    @DeleteMapping("/{id}")
    public String deleteShipment(@PathVariable Long id) {
        Shipment existing = service.getById(id);
        if (existing == null) {
            return "Shipment not found with id " + id;
        }
        service.delete(id);
        return "Shipment deleted successfully with id " + id;
    }
}
