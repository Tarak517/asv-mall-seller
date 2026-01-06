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

    
    @PostMapping
    public Product create(@RequestBody Product p) {

        
        Long storeId = 1L;

        p.setProductId(null);             // ensure insert
        p.setUuid(null);                  // generated in service
        p.setStoreId(storeId);            // ✅ REQUIRED
        p.setStatus(ProductStatus.DRAFT); // draft by default
        p.setCreatedAt(LocalDateTime.now());
        p.setUpdatedAt(LocalDateTime.now());

        return productService.save(p);
    }

    // =========================
    // CREATE WITH IMAGE (MULTIPART)
    // =========================
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

   
    @GetMapping
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return productService.get(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "Product deleted successfully";
    }
}
