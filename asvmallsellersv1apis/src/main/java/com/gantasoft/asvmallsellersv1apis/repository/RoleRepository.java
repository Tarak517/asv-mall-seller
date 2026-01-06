package com.gantasoft.asvmallsellersv1apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gantasoft.asvmallsellersv1apis.entity.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
}
