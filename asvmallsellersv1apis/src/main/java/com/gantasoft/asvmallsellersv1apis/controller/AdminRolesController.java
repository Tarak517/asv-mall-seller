package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.AdminRoles;

import com.gantasoft.asvmallsellersv1apis.service.AdminRolesService;
import com.gantasoft.asvmallsellersv1apis.repository.AdminRepository;
import com.gantasoft.asvmallsellersv1apis.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adminroles")
public class AdminRolesController {

    @Autowired
    private AdminRolesService adminRolesService;

    @Autowired
    private AdminRepository adminsRepository;

    @Autowired
    private RoleRepository rolesRepository;

    @GetMapping
    public List<AdminRoles> getAll() {
        return adminRolesService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<AdminRoles> getById(@PathVariable Long id) {
        return adminRolesService.getById(id);
    }

    // DTO class for request
    public static class AdminRoleRequest {
        public Long adminId;
        public Long roleId;
    }

    @PostMapping
    public AdminRoles create(@RequestBody AdminRoleRequest request) {
        AdminRoles adminRoles = new AdminRoles();

        var admin = adminsRepository.findById(request.adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        var role = rolesRepository.findById(request.roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        adminRoles.setAdmin(admin);
        adminRoles.setRole(role);

        return adminRolesService.save(adminRoles);
    }

    @PutMapping("/{id}")
    public AdminRoles update(@PathVariable Long id, @RequestBody AdminRoleRequest request) {
        AdminRoles adminRoles = adminRolesService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("AdminRole not found"));

        var admin = adminsRepository.findById(request.adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        var role = rolesRepository.findById(request.roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        adminRoles.setAdmin(admin);
        adminRoles.setRole(role);

        return adminRolesService.save(adminRoles);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        adminRolesService.delete(id);
    }
}
