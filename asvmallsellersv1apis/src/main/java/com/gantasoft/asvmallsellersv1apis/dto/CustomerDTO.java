package com.gantasoft.asvmallsellersv1apis.dto;

import com.gantasoft.asvmallsellersv1apis.entity.Customer;

public class CustomerDTO {
    private Long customerId;
    private String fullName;
    private String email;
    private String phone;
    private Customer.Status status;

    // Constructor matching the parameters
    public CustomerDTO(Long customerId, String fullName, String email, String phone, Customer.Status status) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    // Getters and setters
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Customer.Status getStatus() { return status; }
    public void setStatus(Customer.Status status) { this.status = status; }
}
