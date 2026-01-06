package com.gantasoft.asvmallsellersv1apis.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CustomerAddressId implements Serializable {

   
	private static final long serialVersionUID = 1L;
	private Long customerId;
    private Long addressId;

    // --- Constructors ---
    public CustomerAddressId() {
    	
    }

    public CustomerAddressId(Long customerId, Long addressId) {
        this.customerId = customerId;
        this.addressId = addressId;
    }

    // --- Getters and Setters ---
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, addressId);
    }
}
