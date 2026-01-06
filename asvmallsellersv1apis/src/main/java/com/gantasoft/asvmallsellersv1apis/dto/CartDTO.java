package com.gantasoft.asvmallsellersv1apis.dto;

import com.gantasoft.asvmallsellersv1apis.enums.CartStatus;

public class CartDTO {
    private Long cartId;
    private String currency;
    private CartStatus status;
    private CustomerDTO customer;

    public CartDTO(Long cartId, String currency, CartStatus cartStatus, CustomerDTO customer) {
        this.cartId = cartId;
        this.currency = currency;
        this.status = cartStatus;
        this.customer = customer;
    }

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public CartStatus getStatus() {
		return status;
	}

	public void setStatus(CartStatus status) {
		this.status = status;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

    
}
