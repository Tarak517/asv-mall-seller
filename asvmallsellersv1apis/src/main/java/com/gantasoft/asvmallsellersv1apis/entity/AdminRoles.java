package com.gantasoft.asvmallsellersv1apis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "admin_roles")
public class AdminRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)   // ✅ CHANGE HERE
    @JoinColumn(name = "admin_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Admins admin;

    @ManyToOne(fetch = FetchType.EAGER)   // ✅ CHANGE HERE
    @JoinColumn(name = "role_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Roles role;

    public AdminRoles() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Admins getAdmin() { return admin; }
    public void setAdmin(Admins admin) { this.admin = admin; }

    public Roles getRole() { return role; }
    public void setRole(Roles role) { this.role = role; }
}
