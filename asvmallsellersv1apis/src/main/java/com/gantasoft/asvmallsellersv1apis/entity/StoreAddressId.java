package com.gantasoft.asvmallsellersv1apis.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StoreAddressId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long storeId;
    private Long addressId;

   
    public StoreAddressId() {}

   
    public StoreAddressId(Long storeId, Long addressId) {
        this.storeId = storeId;
        this.addressId = addressId;
    }

    // Getters and Setters
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    
}
