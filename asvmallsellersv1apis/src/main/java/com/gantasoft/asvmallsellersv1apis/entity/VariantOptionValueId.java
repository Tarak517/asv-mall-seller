package com.gantasoft.asvmallsellersv1apis.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VariantOptionValueId implements Serializable {

    private Long variantId;
    private Long valueId;

    public VariantOptionValueId() {}

    public VariantOptionValueId(Long variantId, Long valueId) {
        this.variantId = variantId;
        this.valueId = valueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VariantOptionValueId)) return false;
        VariantOptionValueId that = (VariantOptionValueId) o;
        return Objects.equals(variantId, that.variantId) &&
               Objects.equals(valueId, that.valueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variantId, valueId);
    }

	public Long getVariantId() {
		return variantId;
	}

	public void setVariantId(Long variantId) {
		this.variantId = variantId;
	}

	public Long getValueId() {
		return valueId;
	}

	public void setValueId(Long valueId) {
		this.valueId = valueId;
	}

    
}
