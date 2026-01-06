package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.AuditLog;
import com.gantasoft.asvmallsellersv1apis.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditLogService {

    private final AuditLogRepository repository;

    public AuditLogService(AuditLogRepository repository) {
        this.repository = repository;
    }

    public AuditLog save(AuditLog auditLog) {
        return repository.save(auditLog);
    }

    public List<AuditLog> findAll() {
        return repository.findAll();
    }

    public AuditLog findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
