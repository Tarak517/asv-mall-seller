package com.gantasoft.asvmallsellersv1apis.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class InventoryLocationId implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long variantId;
    private Long locationId;

    public InventoryLocationId() {}

    public InventoryLocationId(Long variantId, Long locationId) {
        this.variantId = variantId;
        this.locationId = locationId;
    }

    public Long getVariantId() { return variantId; }
    public Long getLocationId() { return locationId; }
}
