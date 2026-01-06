package com.gantasoft.asvmallsellersv1apis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gantasoft.asvmallsellersv1apis.entity.CartItems;
import com.gantasoft.asvmallsellersv1apis.repository.CartItemRepository;

@Service
public class CartItemsService {

    private final CartItemRepository cartItemRepository;

    public CartItemsService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    // CREATE / UPDATE
    public CartItems save(CartItems cartItems) {
        return cartItemRepository.save(cartItems);
    }

    // ✅ REQUIRED BY CONTROLLER
    public List<CartItems> findAll() {
        return cartItemRepository.findAll();
    }

    // ✅ REQUIRED BY CONTROLLER
    public Optional<CartItems> findById(Long id) {
        return cartItemRepository.findById(id);
    }

    // ✅ REQUIRED BY CONTROLLER
    public List<CartItems> findByCartId(Long cartId) {
        return cartItemRepository.findByCartCartId(cartId);
    }

    // ✅ REQUIRED BY CONTROLLER
    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }

    // ✅ REQUIRED BY CONTROLLER
    public void deleteByCartId(Long cartId) {
        cartItemRepository.deleteByCartCartId(cartId);
    }
}
