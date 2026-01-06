package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.Product;
import com.gantasoft.asvmallsellersv1apis.entity.ProductImage;
import com.gantasoft.asvmallsellersv1apis.repository.ProductImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductImageService {

    private final ProductImageRepository repo;

    public ProductImageService(ProductImageRepository repo) {
        this.repo = repo;
    }

    // Save a ProductImage entity
    public ProductImage save(ProductImage image) {
        return repo.save(image);
    }

    // Save an image for a Product
    public void saveImage(Product product, MultipartFile image) {
        try {
            if (image == null || image.isEmpty()) return;

            // For simplicity, store only the filename as URL
            String filename = image.getOriginalFilename();

            ProductImage productImage = new ProductImage();
            productImage.setProduct(product);          // Link to Product
            productImage.setUrl(filename);             // Save filename (or path)
            productImage.setCreatedAt(LocalDateTime.now());
            productImage.setUpdatedAt(LocalDateTime.now());

            repo.save(productImage);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save image", e);
        }
    }

    // Get all product images
    public List<ProductImage> getAll() {
        return repo.findAll();
    }

    // Get images by product ID
    public List<ProductImage> getByProductId(Long productId) {
        return repo.findByProductProductId(productId);
    }

    // Delete image by ID
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
