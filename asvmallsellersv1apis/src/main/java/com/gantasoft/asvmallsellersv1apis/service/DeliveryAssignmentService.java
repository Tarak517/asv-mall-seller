package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.DeliveryAssignment;
import com.gantasoft.asvmallsellersv1apis.repository.DeliveryAssignmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeliveryAssignmentService {

    private final DeliveryAssignmentRepository repository;

    public DeliveryAssignmentService(DeliveryAssignmentRepository repository) {
        this.repository = repository;
    }

    public DeliveryAssignment save(DeliveryAssignment da) {
        return repository.save(da);
    }
    
    public List<DeliveryAssignment> getAll() {
        return repository.findAll();
    }


    public List<DeliveryAssignment> getByDeliveryBoyId(Long deliveryBoyId) {
        return repository.findByDeliveryBoyId(deliveryBoyId);
    }

    public List<DeliveryAssignment> getByShipmentId(Long shipmentId) {
        return repository.findByShipmentId(shipmentId);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
