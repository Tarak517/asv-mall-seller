package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.Admins;
import com.gantasoft.asvmallsellersv1apis.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceController {
    private final AdminRepository adminRepository;
    
    public AdminServiceController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    public Admins save(Admins admin) {
        return adminRepository.save(admin);
    }
    public List<Admins> findAll() {
        return adminRepository.findAll();
    }
    public Admins findById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }
    public void delete(Long id) {
        adminRepository.deleteById(id);
    }
}
