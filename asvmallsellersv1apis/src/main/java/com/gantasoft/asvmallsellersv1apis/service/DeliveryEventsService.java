package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.DeliveryAssignment;
import com.gantasoft.asvmallsellersv1apis.entity.DeliveryEvents;
import com.gantasoft.asvmallsellersv1apis.repository.DeliveryAssignmentRepository;
import com.gantasoft.asvmallsellersv1apis.repository.DeliveryEventsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryEventsService {

    private final DeliveryEventsRepository eventsRepo;
    private final DeliveryAssignmentRepository assignmentRepo;

    public DeliveryEventsService(DeliveryEventsRepository eventsRepo, DeliveryAssignmentRepository assignmentRepo) {
        this.eventsRepo = eventsRepo;
        this.assignmentRepo = assignmentRepo;
    }

    public DeliveryEvents save(Long assignmentId, DeliveryEvents event) {
        DeliveryAssignment assignment = assignmentRepo.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found with id: " + assignmentId));
        event.setAssignment(assignment);
        return eventsRepo.save(event);
    }

    public List<DeliveryEvents> findAll() {
        return eventsRepo.findAll();
    }

    public DeliveryEvents findById(Long id) {
        return eventsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }

    public void delete(Long id) {
        eventsRepo.deleteById(id);
    }
}
