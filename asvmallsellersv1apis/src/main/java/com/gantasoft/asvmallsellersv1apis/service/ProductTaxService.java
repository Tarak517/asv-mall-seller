package com.gantasoft.asvmallsellersv1apis.service;

import org.springframework.stereotype.Service;
import com.gantasoft.asvmallsellersv1apis.entity.ProductTax;
import com.gantasoft.asvmallsellersv1apis.entity.ProductTaxId;
import com.gantasoft.asvmallsellersv1apis.repository.ProductRepository;
import com.gantasoft.asvmallsellersv1apis.repository.ProductTaxRepository;
import com.gantasoft.asvmallsellersv1apis.repository.TaxesRepository;

import java.util.List;

@Service
public class ProductTaxService {

    private final ProductTaxRepository repo;
    private final ProductRepository productRepo;
    private final TaxesRepository taxRepo;

    public ProductTaxService(
            ProductTaxRepository repo,
            ProductRepository productRepo,
            TaxesRepository taxRepo) {
        this.repo = repo;
        this.productRepo = productRepo;
        this.taxRepo = taxRepo;
    }

    public ProductTax save(Long productId, Long taxId) {

        var product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        var tax = taxRepo.findById(taxId)
                .orElseThrow(() -> new RuntimeException("Tax not found"));

        var id = new ProductTaxId(productId, taxId);

        ProductTax pt = new ProductTax();
        pt.setId(id);      // required
        pt.setProduct(product);  // required
        pt.setTax(tax);    // required

        return repo.save(pt);
    }
    public ProductTax save(ProductTax pt) {
        return repo.save(pt);
    }


    public List<ProductTax> getAll() {
        return repo.findAll();
    }

    public ProductTax getById(Long productId, Long taxId) {
        return repo.findById(new ProductTaxId(productId, taxId)).orElse(null);
    }

    public void delete(Long productId, Long taxId) {
        repo.deleteById(new ProductTaxId(productId, taxId));
    }
}