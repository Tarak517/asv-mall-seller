package com.gantasoft.asvmallsellersv1apis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_category")
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ProductCategoryId id;

    @JsonIgnore
    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonIgnore
    @MapsId("categoryId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Categories category;

    public ProductCategory() {}

    public ProductCategory(ProductCategoryId id) {
        this.id = id;
    }

    public ProductCategory(Long productId, Long categoryId) {
        this.id = new ProductCategoryId(productId, categoryId);
    }

    // ✅ REQUIRED setters
    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public ProductCategoryId getId() {
        return id;
    }

    public void setId(ProductCategoryId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public Categories getCategory() {
        return category;
    }
}
