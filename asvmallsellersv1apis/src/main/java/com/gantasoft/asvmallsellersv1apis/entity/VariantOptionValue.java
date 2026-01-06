package com.gantasoft.asvmallsellersv1apis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "variant_option_values")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VariantOptionValue {

    @EmbeddedId
    private VariantOptionValueId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("variantId")
    @JoinColumn(name = "variant_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ProductVariant variant;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("valueId")
    @JoinColumn(name = "value_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ProductOptionValue value;

    public VariantOptionValue() {}

    public VariantOptionValue(ProductVariant variant, ProductOptionValue value) {
        this.variant = variant;
        this.value = value;
        this.id = new VariantOptionValueId(variant.getVariantId(), value.getValueId());
    }

	public VariantOptionValueId getId() {
		return id;
	}

	public void setId(VariantOptionValueId id) {
		this.id = id;
	}

	public ProductVariant getVariant() {
		return variant;
	}

	public void setVariant(ProductVariant variant) {
		this.variant = variant;
	}

	public ProductOptionValue getValue() {
		return value;
	}

	public void setValue(ProductOptionValue value) {
		this.value = value;
	}

    
}
