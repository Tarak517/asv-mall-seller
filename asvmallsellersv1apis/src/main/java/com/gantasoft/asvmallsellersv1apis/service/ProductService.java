package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.Product;
import com.gantasoft.asvmallsellersv1apis.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    // =========================
    // SAVE PRODUCT
    // =========================
    public Product save(Product p) {

        // UUID
        if (p.getUuid() == null) {
            p.setUuid(UUID.randomUUID().toString());
        }

        // SAFETY: product name fallback
        String baseName = (p.getName() != null && !p.getName().isBlank())
                ? p.getName()
                : "product";

        // SLUG
        if (p.getSlug() == null || p.getSlug().isBlank()) {
            p.setSlug(generateUniqueSlug(baseName));
        } else {
            Product existing = repo.findBySlug(p.getSlug());
            if (existing != null && !existing.getProductId().equals(p.getProductId())) {
                p.setSlug(generateUniqueSlug(p.getSlug()));
            }
        }

        return repo.save(p);
    }

    // =========================
    // SLUG GENERATOR (SAFE)
    // =========================
    private String generateUniqueSlug(String base) {
        String slug = base
                .toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-|-$", "");

        if (slug.isBlank()) {
            slug = "product";
        }

        String original = slug;
        int counter = 1;

        while (repo.findBySlug(slug) != null) {
            slug = original + "-" + counter++;
        }

        return slug;
    }

    // =========================
    // READ
    // =========================
    public Product get(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Product getByUuid(String uuid) {
        return repo.findByUuid(uuid);
    }

    public Product getBySlug(String slug) {
        return repo.findBySlug(slug);
    }

    public List<Product> findAll() {
        return repo.findAll();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
