package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.RolePermission;
import com.gantasoft.asvmallsellersv1apis.repository.RolePermissionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RolePermissionService {

    private final RolePermissionRepository repo;

    public RolePermissionService(RolePermissionRepository repo) {
        this.repo = repo;
    }

    public RolePermission save(RolePermission rp) {
        return repo.save(rp);
    }

    public List<RolePermission> getAll() {
        return repo.findAll();
    }

    public RolePermission getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
