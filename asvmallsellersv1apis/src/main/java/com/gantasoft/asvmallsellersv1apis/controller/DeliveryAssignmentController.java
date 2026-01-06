package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.DeliveryAssignment;
import com.gantasoft.asvmallsellersv1apis.service.DeliveryAssignmentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/delivery-assignments")
public class DeliveryAssignmentController {

    private final DeliveryAssignmentService service;

    public DeliveryAssignmentController(DeliveryAssignmentService service) {
        this.service = service;
    }

    @PostMapping
    public DeliveryAssignment create(@RequestBody DeliveryAssignment da) {
        return service.save(da);
    }
    
    @GetMapping
    public List<DeliveryAssignment> getAll() {
        return service.getAll(); 
    }


    @GetMapping("/delivery-boy/{deliveryBoyId}")
    public List<DeliveryAssignment> getByDeliveryBoy(@PathVariable Long deliveryBoyId) {
        return service.getByDeliveryBoyId(deliveryBoyId);
    }

    @GetMapping("/shipment/{shipmentId}")
    public List<DeliveryAssignment> getByShipment(@PathVariable Long shipmentId) {
        return service.getByShipmentId(shipmentId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
