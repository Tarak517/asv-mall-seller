package com.gantasoft.asvmallsellersv1apis.repository;

import com.gantasoft.asvmallsellersv1apis.entity.Otp;
import com.gantasoft.asvmallsellersv1apis.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {

    Optional<Otp> findTopByUserIdAndUserTypeOrderByCreatedAtDesc(Long userId, UserType userType);

    Optional<Otp> findByUserIdAndCodeAndIsUsed(Long userId, String code, Boolean isUsed);
}
