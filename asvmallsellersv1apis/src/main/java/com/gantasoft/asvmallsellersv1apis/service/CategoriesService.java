package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.Categories;
import com.gantasoft.asvmallsellersv1apis.repository.CategoriesRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriesService {

    private final CategoriesRepository repository;

    public CategoriesService(CategoriesRepository repository) {
        this.repository = repository;
    }

    public List<Categories> findAll() {
        return repository.findAll();
    }

    public Categories findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Categories save(Categories category) {

        
        if (category.getSlug() == null || category.getSlug().isBlank()) {
            category.setSlug(
                    category.getName()
                            .toLowerCase()
                            .replaceAll("[^a-z0-9]+", "-")
            );
        }

        return repository.save(category);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
