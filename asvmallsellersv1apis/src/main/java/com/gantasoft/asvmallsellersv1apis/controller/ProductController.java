package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.Product;
import com.gantasoft.asvmallsellersv1apis.enums.ProductStatus;
import com.gantasoft.asvmallsellersv1apis.service.ProductImageService;
import com.gantasoft.asvmallsellersv1apis.service.ProductService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")

public class ProductController {

    private final ProductService productService;
    private final ProductImageService productImageService;

    public ProductController(ProductService productService,
                             ProductImageService productImageService) {
        this.productService = productService;
        this.productImageService = productImageService;
    }

   
    // ====CREATE PRODUCT====
  
    @PostMapping
    public Product create(@RequestBody Product p) {

        Long storeId = 1L;

        p.setProductId(null);
        p.setStoreId(storeId);

        if (p.getStatus() == null) {
            p.setStatus(ProductStatus.DRAFT);
        }

        p.setCreatedAt(LocalDateTime.now());
        p.setUpdatedAt(LocalDateTime.now());

        return productService.save(p);
    }


    // ======CREATE PRODUCT WITH IMAGE
    
    @PostMapping(
            value = "/with-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public Product createWithImage(
            @RequestParam String title,
            @RequestParam Long storeId,
            @RequestParam String status,
            @RequestParam(required = false) String slug,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) MultipartFile image
    ) {

        Product product = new Product();

        product.setName(title);
        product.setStoreId(storeId);
        product.setDescription(description);
        product.setSlug(slug);
        product.setStatus(ProductStatus.valueOf(status));

        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product saved = productService.save(product);

        if (image != null && !image.isEmpty()) {
            productImageService.saveImage(saved, image);
        }

        return saved;
    }

  
    // =========GET ALL PRODUCTS
    
    @GetMapping
    public List<Product> getAll() {
        return productService.findAll();
    }


    // GET PRODUCT BY ID
    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return productService.get(id);
    }

    // UPDATE PRODUCT
   
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) {

        Product existing = productService.get(id);

        existing.setName(p.getName());
        existing.setDescription(p.getDescription());
        existing.setSku(p.getSku());
        existing.setPrice(p.getPrice());
        existing.setStock(p.getStock());
        existing.setCategory(p.getCategory());
        existing.setStatus(p.getStatus());

        existing.setUpdatedAt(LocalDateTime.now());

        return productService.save(existing);
    }

    // =========================
    // DELETE PRODUCT
    // =========================
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "Product deleted successfully";
    }
}