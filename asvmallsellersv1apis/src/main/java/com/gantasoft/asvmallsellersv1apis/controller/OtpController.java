package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.Otp;
import com.gantasoft.asvmallsellersv1apis.service.OtpService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    private final OtpService service;

    public OtpController(OtpService service) {
        this.service = service;
    }

    @PostMapping("/send")
    public Otp sendOtp(@RequestBody Otp otp) {
        return service.save(otp);
    }

    @PostMapping("/verify")
    public boolean verifyOtp(
            @RequestParam Long userId,
            @RequestParam String code) {

        return service.verifyOtp(userId, code);
    }

    
    @GetMapping("/all")
    public List<Otp> getAllOtps() {
        return service.getAll();
    }

    
    @GetMapping("/{id}")
    public Otp getOtpById(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }
}
