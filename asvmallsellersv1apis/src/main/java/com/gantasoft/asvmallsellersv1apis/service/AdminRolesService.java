package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.AdminRoles;
import com.gantasoft.asvmallsellersv1apis.repository.AdminRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminRolesService {

    @Autowired
    private AdminRolesRepository adminRolesRepository;

    public List<AdminRoles> getAll() {
        return adminRolesRepository.findAll();
    }

    public Optional<AdminRoles> getById(Long id) {
        return adminRolesRepository.findById(id);
    }

    public AdminRoles save(AdminRoles adminRoles) {
        return adminRolesRepository.save(adminRoles);
    }

    public void delete(Long id) {
        adminRolesRepository.deleteById(id);
    }
    
}
