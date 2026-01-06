package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.AuditLog;
import com.gantasoft.asvmallsellersv1apis.service.AuditLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogController {

    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    // 1️⃣ Get all audit logs
    @GetMapping
    public ResponseEntity<List<AuditLog>> getAllLogs() {
        List<AuditLog> logs = auditLogService.findAll();
        return ResponseEntity.ok(logs);
    }

    // 2️⃣ Get audit log by ID
    @GetMapping("/{id}")
    public ResponseEntity<AuditLog> getLogById(@PathVariable Long id) {
        AuditLog log = auditLogService.findById(id);
        if (log == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(log);
    }

    // 3️⃣ Create a new audit log
    @PostMapping
    public ResponseEntity<AuditLog> createLog(@RequestBody AuditLog auditLog) {
        AuditLog savedLog = auditLogService.save(auditLog);
        return ResponseEntity.ok(savedLog);
    }
}
