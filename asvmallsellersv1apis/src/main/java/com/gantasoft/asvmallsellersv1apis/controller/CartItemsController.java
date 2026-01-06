package com.gantasoft.asvmallsellersv1apis.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.gantasoft.asvmallsellersv1apis.dto.CartDTO;
import com.gantasoft.asvmallsellersv1apis.dto.CartItemResponse;
import com.gantasoft.asvmallsellersv1apis.dto.CustomerDTO;
import com.gantasoft.asvmallsellersv1apis.entity.Cart;
import com.gantasoft.asvmallsellersv1apis.entity.CartItems;
import com.gantasoft.asvmallsellersv1apis.entity.ProductVariant;
import com.gantasoft.asvmallsellersv1apis.service.CartItemsService;
import com.gantasoft.asvmallsellersv1apis.service.CartService;
import com.gantasoft.asvmallsellersv1apis.service.ProductVariantService;

@RestController
@RequestMapping("/cart-items")
public class CartItemsController {

    private final CartItemsService cartItemsService;
    private final CartService cartService;
    private final ProductVariantService productVariantService;

    public CartItemsController(CartItemsService cartItemsService, CartService cartService, ProductVariantService productVariantService) {
        this.cartItemsService = cartItemsService;
        this.cartService = cartService;
        this.productVariantService = productVariantService;
    }

    // CREATE cart item using IDs
    @PostMapping
    public CartItemResponse create(@RequestBody Map<String, Object> request) {
        Long cartId = Long.valueOf(request.get("cartId").toString());
        Long variantId = Long.valueOf(request.get("variantId").toString());
        Integer qty = Integer.valueOf(request.get("qty").toString());

        Cart cart = cartService.findById(cartId);
        ProductVariant variant = productVariantService.getById(variantId);

        if (cart == null || variant == null) {
            throw new RuntimeException("Cart or Variant not found");
        }

        CartItems item = new CartItems();
        item.setCart(cart);
        item.setVariant(variant);
        item.setQty(qty);
        item.setUnitPrice(BigDecimal.valueOf(variant.getPrice()));
        item.setTaxAmount(BigDecimal.ZERO);
        item.setDiscountAmount(BigDecimal.ZERO);

        CartItems savedItem = cartItemsService.save(item);

        return mapToDTO(savedItem);
    }

    // GET all cart items
    @GetMapping
    public List<CartItemResponse> getAll() {
        return cartItemsService.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // GET cart item by item_id
    @GetMapping("/{id}")
    public CartItemResponse getById(@PathVariable Long id) {
        CartItems item = cartItemsService.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        return mapToDTO(item);
    }

    // GET cart items by cart_id
    @GetMapping("/cart/{cartId}")
    public List<CartItemResponse> getByCartId(@PathVariable Long cartId) {
        return cartItemsService.findByCartId(cartId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // DELETE cart item by item_id
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cartItemsService.deleteById(id);
    }

    // DELETE all items by cart_id
    @DeleteMapping("/cart/{cartId}")
    public void deleteByCartId(@PathVariable Long cartId) {
        cartItemsService.deleteByCartId(cartId);
    }

    // --- Helper method to map entity to DTO ---
    private CartItemResponse mapToDTO(CartItems item) {
        Cart cart = item.getCart();
        ProductVariant variant = item.getVariant();

        CustomerDTO customerDTO = new CustomerDTO(
                cart.getCustomer().getCustomerId(),
                cart.getCustomer().getFullName(),
                cart.getCustomer().getEmail(),
                cart.getCustomer().getPhone(),
                cart.getCustomer().getStatus()
        );

        CartDTO cartDTO = new CartDTO(
                cart.getCartId(),
                cart.getCurrency(),
                cart.getStatus(),
                customerDTO
        );

        return new CartItemResponse(
                item.getItemId(),
                cartDTO,
                variant.getVariantId(),
                item.getQty(),
                item.getUnitPrice(),
                item.getTaxAmount(),
                item.getDiscountAmount()
        );
    }
}
