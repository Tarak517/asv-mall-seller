package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.Roles;
import com.gantasoft.asvmallsellersv1apis.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

   
    @PostMapping
    public Roles createRole(@RequestBody Roles role) {
        return service.save(role);
    }

    @GetMapping
    public List<Roles> getAllRoles() {
        return service.getAll();
    }

   
    @GetMapping("/{id}")
    public Roles getRoleById(@PathVariable Long id) {
        return service.getById(id);
    }

  
    @PutMapping("/{id}")
    public Roles updateRole(@PathVariable Long id, @RequestBody Roles role) {
        Roles existingRole = service.getById(id);
        if (existingRole != null) {
            existingRole.setName(role.getName()); 
            return service.save(existingRole);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        service.delete(id);
    }
}
