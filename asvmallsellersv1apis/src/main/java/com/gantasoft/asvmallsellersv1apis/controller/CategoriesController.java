package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.Categories;
import com.gantasoft.asvmallsellersv1apis.service.CategoriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    private final CategoriesService service;

    public CategoriesController(CategoriesService service) {
        this.service = service;
    }

    
    @GetMapping
    public ResponseEntity<List<Categories>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Categories> getById(@PathVariable Long id) {
        Categories category = service.findById(id);
        return ResponseEntity.ok(category);
    }

   
    @PostMapping
    public ResponseEntity<Categories> create(@RequestBody Categories category) {
        Categories saved = service.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

  
    @PutMapping("/{id}")
    public ResponseEntity<Categories> update(
            @PathVariable Long id,
            @RequestBody Categories category) {

        category.setCategoryId(id);
        Categories updated = service.save(category);
        return ResponseEntity.ok(updated);
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
