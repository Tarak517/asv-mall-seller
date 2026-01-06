package com.gantasoft.asvmallsellersv1apis.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory_location")
public class InventoryLocation {

    @EmbeddedId
    private InventoryLocationId id;

    private Integer quantity;

    public InventoryLocation() {}

    public InventoryLocation(InventoryLocationId id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public InventoryLocationId getId() { return id; }
    public Integer getQuantity() { return quantity; }
}
