package com.gantasoft.asvmallsellersv1apis.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductCategoryId implements Serializable {

    private Long productId;
    private Long categoryId;

    // Default constructor (required by JPA)
    public ProductCategoryId() {
    }

    // Constructor with fields
    public ProductCategoryId(Long productId, Long categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }

    // Getters and setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    // Equals and hashCode are required for @EmbeddedId
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductCategoryId)) return false;
        ProductCategoryId that = (ProductCategoryId) o;
        return productId.equals(that.productId) && categoryId.equals(that.categoryId);
    }

    @Override
    public int hashCode() {
        return productId.hashCode() + categoryId.hashCode();
    }
}
