package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.RolePermission;
import com.gantasoft.asvmallsellersv1apis.service.RolePermissionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/role-permissions")
public class RolePermissionController {

    private final RolePermissionService service;

    public RolePermissionController(RolePermissionService service) {
        this.service = service;
    }

    @GetMapping
    public List<RolePermission> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public RolePermission get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public RolePermission create(@RequestBody RolePermission rp) {
        return service.save(rp);
    }

    @PutMapping("/{id}")
    public RolePermission update(@PathVariable Long id, @RequestBody RolePermission rp) {
        RolePermission existing = service.getById(id);
        if (existing != null) {
            existing.setRole(rp.getRole());
            existing.setPermission(rp.getPermission());
            existing.setUpdatedAt(rp.getUpdatedAt());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
