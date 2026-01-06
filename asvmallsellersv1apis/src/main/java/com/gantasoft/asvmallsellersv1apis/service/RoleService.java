package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.Roles;
import com.gantasoft.asvmallsellersv1apis.repository.RoleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepository repo;

    public RoleService(RoleRepository repo) {
        this.repo = repo;
    }

    public Roles save(Roles role) {
        return repo.save(role);
    }

    public List<Roles> getAll() {
        return repo.findAll();
    }

    public Roles getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
