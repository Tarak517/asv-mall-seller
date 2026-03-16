package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.Product;
import com.gantasoft.asvmallsellersv1apis.entity.ProductImage;
import com.gantasoft.asvmallsellersv1apis.repository.ProductImageRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Service
public class ProductImageService {

    private final ProductImageRepository repo;
    private final Path uploadPath = Paths.get("uploads");

    public ProductImageService(ProductImageRepository repo) {
        this.repo = repo;

        try {
            Files.createDirectories(uploadPath);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload folder");
        }
    }

    public ProductImage saveImage(Product product, MultipartFile file) {

        try {

            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path target = uploadPath.resolve(filename);

            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

            ProductImage image = new ProductImage();

            image.setProduct(product);
            image.setUrl(filename);

            List<ProductImage> images = repo.findByProductProductId(product.getProductId());

            image.setSortOrder(images.size());
            image.setIsPrimary(images.isEmpty());

            return repo.save(image);

        } catch (IOException e) {
            throw new RuntimeException("File upload failed");
        }
    }

    public List<ProductImage> getByProductId(Long productId) {
        return repo.findByProductProductId(productId);
    }

    public ProductImage getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Resource loadFile(String filename) {

        try {

            Path file = uploadPath.resolve(filename).normalize();

            return new UrlResource(file.toUri());

        } catch (Exception e) {
            throw new RuntimeException("File not found");
        }
    }

    public void delete(Long id) {

        ProductImage img = getById(id);

        try {

            Files.deleteIfExists(uploadPath.resolve(img.getUrl()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        repo.deleteById(id);
    }
}