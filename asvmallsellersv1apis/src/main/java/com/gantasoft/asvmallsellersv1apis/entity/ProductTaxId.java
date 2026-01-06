package com.gantasoft.asvmallsellersv1apis.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductTaxId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long productId;
    private Long taxId;

    public ProductTaxId() {
    }

    public ProductTaxId(Long productId, Long taxId) {
        this.productId = productId;
        this.taxId = taxId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductTaxId)) return false;
        ProductTaxId that = (ProductTaxId) o;
        return Objects.equals(productId, that.productId) &&
               Objects.equals(taxId, that.taxId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, taxId);
    }
}
