package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.Cart;
import com.gantasoft.asvmallsellersv1apis.entity.Customer;
import com.gantasoft.asvmallsellersv1apis.service.CartService;
import com.gantasoft.asvmallsellersv1apis.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/carts")
public class CartsController {

    private final CartService cartService;
    private final CustomerService customerService;

    public CartsController(CartService cartService, CustomerService customerService) {
        this.cartService = cartService;
        this.customerService = customerService;
    }

    // Get all carts
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(cartService.findAll());
    }

    // Get a single cart by ID
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        Cart cart = cartService.findById(id);
        if (cart == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cart);
    }

    // Create a new cart
    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Map<String, Object> request) {
        // Validate input
        Object customerIdObj = request.get("customerId");
        if (customerIdObj == null) return ResponseEntity.badRequest().build();

        Long customerId = Long.valueOf(customerIdObj.toString());
        Customer customer = customerService.findById(customerId);
        if (customer == null) return ResponseEntity.badRequest().build();

        // Create Cart
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setCurrency(request.getOrDefault("currency", "INR").toString());

        Cart savedCart = cartService.save(cart);
        return ResponseEntity.ok(savedCart);
    }

    // Update an existing cart
    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        Cart existingCart = cartService.findById(id);
        if (existingCart == null) return ResponseEntity.notFound().build();

        if (request.containsKey("currency")) {
            existingCart.setCurrency(request.get("currency").toString());
        }

        Cart updatedCart = cartService.save(existingCart);
        return ResponseEntity.ok(updatedCart);
    }

    // Delete a cart
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        Cart cart = cartService.findById(id);
        if (cart == null) return ResponseEntity.notFound().build();

        cartService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
