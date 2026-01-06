package com.gantasoft.asvmallsellersv1apis.repository;

import com.gantasoft.asvmallsellersv1apis.entity.AdminRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRolesRepository extends JpaRepository<AdminRoles, Long> {

    @Query("""
        SELECT ar
        FROM AdminRoles ar
        JOIN FETCH ar.admin
        JOIN FETCH ar.role
    """)
    List<AdminRoles> findAllWithDetails();

    @Query("""
        SELECT ar
        FROM AdminRoles ar
        JOIN FETCH ar.admin
        JOIN FETCH ar.role
        WHERE ar.admin.adminId = :adminId
    """)
    List<AdminRoles> findByAdminIdWithDetails(Long adminId);

    @Query("""
        SELECT ar
        FROM AdminRoles ar
        JOIN FETCH ar.admin
        JOIN FETCH ar.role
        WHERE ar.role.roleId = :roleId
    """)
    List<AdminRoles> findByRoleIdWithDetails(Long roleId);
}
