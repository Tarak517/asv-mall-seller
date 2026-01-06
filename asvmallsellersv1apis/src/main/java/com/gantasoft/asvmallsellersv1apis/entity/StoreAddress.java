package com.gantasoft.asvmallsellersv1apis.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreAddress {

    @EmbeddedId
    private StoreAddressId id;

    @Column(name = "type")
    private String type;

    public Long getStoreId() {
        return id.getStoreId();
    }

    public void setStoreId(Long storeId) {
        id.setStoreId(storeId);
    }

    public Long getAddressId() {
        return id.getAddressId();
    }

    public void setAddressId(Long addressId) {
        id.setAddressId(addressId);
    }

	public StoreAddressId getId() {
		return id;
	}

	public void setId(StoreAddressId id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    
}
