package com.gantasoft.asvmallsellersv1apis.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "variant_id", nullable = false)
    private Long variantId;

    @Column(name = "location_id", nullable = false)
    private Long locationId;

    @Column(name = "qty_available", nullable = false, columnDefinition = "int default 0")
    private Integer qtyAvailable = 0;

    @Column(name = "qty_reserved", nullable = false, columnDefinition = "int default 0")
    private Integer qtyReserved = 0;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getVariantId() { return variantId; }
    public void setVariantId(Long variantId) { this.variantId = variantId; }

    public Long getLocationId() { return locationId; }
    public void setLocationId(Long locationId) { this.locationId = locationId; }

    public Integer getQtyAvailable() { return qtyAvailable; }
    public void setQtyAvailable(Integer qtyAvailable) { this.qtyAvailable = qtyAvailable; }

    public Integer getQtyReserved() { return qtyReserved; }
    public void setQtyReserved(Integer qtyReserved) { this.qtyReserved = qtyReserved; }
}
