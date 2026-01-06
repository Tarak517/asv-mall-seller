package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.Admins;
import com.gantasoft.asvmallsellersv1apis.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admins> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admins> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    public Admins saveAdmin(Admins admin) {
        return adminRepository.save(admin);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public Optional<Admins> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}
