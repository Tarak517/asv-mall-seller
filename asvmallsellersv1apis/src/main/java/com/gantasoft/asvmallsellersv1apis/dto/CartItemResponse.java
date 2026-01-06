package com.gantasoft.asvmallsellersv1apis.dto;

import java.math.BigDecimal;

public class CartItemResponse {
    private Long itemId;
    private CartDTO cart;
    private Long variantId;
    private Integer qty;
    private BigDecimal unitPrice;
    private BigDecimal taxAmount;
    private BigDecimal discountAmount;

    // Constructor matching all fields
    public CartItemResponse(Long itemId, CartDTO cart, Long variantId, Integer qty,
                            BigDecimal unitPrice, BigDecimal taxAmount, BigDecimal discountAmount) {
        this.itemId = itemId;
        this.cart = cart;
        this.variantId = variantId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.taxAmount = taxAmount;
        this.discountAmount = discountAmount;
    }

    // Getters and setters
    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }
    public CartDTO getCart() { return cart; }
    public void setCart(CartDTO cart) { this.cart = cart; }
    public Long getVariantId() { return variantId; }
    public void setVariantId(Long variantId) { this.variantId = variantId; }
    public Integer getQty() { return qty; }
    public void setQty(Integer qty) { this.qty = qty; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public BigDecimal getTaxAmount() { return taxAmount; }
    public void setTaxAmount(BigDecimal taxAmount) { this.taxAmount = taxAmount; }
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }
}
