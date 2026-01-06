package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.DeliveryEvents;
import com.gantasoft.asvmallsellersv1apis.service.DeliveryEventsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery-events")
public class DeliveryEventsController {

    private final DeliveryEventsService service;

    public DeliveryEventsController(DeliveryEventsService service) {
        this.service = service;
    }

 
    @PostMapping
    public DeliveryEvents create(@RequestBody DeliveryEvents event) {
        Long assignmentId = event.getAssignmentId();
        if (assignmentId == null) {
            throw new RuntimeException("assignmentId is required in the request");
        }
        return service.save(assignmentId, event);
    }

    @GetMapping
    public List<DeliveryEvents> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public DeliveryEvents getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
