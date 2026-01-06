package com.gantasoft.asvmallsellersv1apis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "product_taxes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductTax {

    @EmbeddedId
    private ProductTaxId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("taxId")
    @JoinColumn(name = "tax_id", nullable = false)
    private Taxes tax;

    public ProductTax() {
    }

    public ProductTax(ProductTaxId id, Product product, Taxes tax) {
        this.id = id;
        this.product = product;
        this.tax = tax;
    }

    public ProductTaxId getId() {
        return id;
    }

    public void setId(ProductTaxId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Taxes getTax() {
        return tax;
    }

    public void setTax(Taxes tax) {
        this.tax = tax;
    }
}
