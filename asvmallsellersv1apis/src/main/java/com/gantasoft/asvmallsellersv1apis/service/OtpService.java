package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.Otp;
import com.gantasoft.asvmallsellersv1apis.enums.UserType;
import com.gantasoft.asvmallsellersv1apis.repository.OtpRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import java.util.List;

@Service
public class OtpService {

    private final OtpRepository repo;

    public OtpService(OtpRepository repo) {
        this.repo = repo;
    }

    public Otp save(Otp otp) {
        return repo.save(otp);
    }

    public Optional<Otp> getLatestOtp(Long userId, UserType userType) {
        return repo.findTopByUserIdAndUserTypeOrderByCreatedAtDesc(userId, userType);
    }

    public boolean verifyOtp(Long userId, String code) {
        Optional<Otp> otp = repo.findByUserIdAndCodeAndIsUsed(userId, code, false);

        if (otp.isEmpty()) return false;

        Otp o = otp.get();

        if (o.getExpiresAt().isBefore(LocalDateTime.now())) return false;

        o.setIsUsed(true);
        repo.save(o);

        return true;
    }

   
    public List<Otp> getAll() {
        return repo.findAll();
    }

    
    public Optional<Otp> getById(Long id) {
        return repo.findById(id);
    }
}
