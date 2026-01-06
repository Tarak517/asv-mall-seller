package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.Permissions;
import com.gantasoft.asvmallsellersv1apis.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    @PostMapping
    public Permissions create(@RequestBody Permissions permission) {
        return service.save(permission);
    }

    @GetMapping
    public List<Permissions> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Permissions getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted";
    }
}
