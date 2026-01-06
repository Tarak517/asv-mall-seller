package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.VariantOptionValue;
import com.gantasoft.asvmallsellersv1apis.entity.VariantOptionValueId;
import com.gantasoft.asvmallsellersv1apis.entity.ProductVariant;
import com.gantasoft.asvmallsellersv1apis.entity.ProductOptionValue;
import com.gantasoft.asvmallsellersv1apis.service.VariantOptionValuesService;
import com.gantasoft.asvmallsellersv1apis.repository.ProductVariantRepository;
import com.gantasoft.asvmallsellersv1apis.repository.VariantOptionValueRepository;
import com.gantasoft.asvmallsellersv1apis.repository.ProductOptionValueRepository;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/variant-option-values")
public class VariantOptionValueController {

    private final VariantOptionValueRepository repository;
    private final ProductVariantRepository variantRepo;
    private final ProductOptionValueRepository valueRepo;
    private final VariantOptionValuesService service;

    public VariantOptionValueController(
            VariantOptionValueRepository repository,
            ProductVariantRepository variantRepo,
            ProductOptionValueRepository valueRepo,
            VariantOptionValuesService service
    ) {
        this.repository = repository;
        this.variantRepo = variantRepo;
        this.valueRepo = valueRepo;
        this.service = service;
    }

    // CREATE
    @PostMapping
    public VariantOptionValue create(@RequestBody VariantOptionValueId request) {
        ProductVariant variant = variantRepo.findById(request.getVariantId())
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        ProductOptionValue value = valueRepo.findById(request.getValueId())
                .orElseThrow(() -> new RuntimeException("Option value not found"));

        VariantOptionValue v = new VariantOptionValue(variant, value);
        return repository.save(v);
    }

    // GET ALL
    @GetMapping
    public List<VariantOptionValue> getAll() {
        return service.getAll();
    }

    // GET BY ID
    @GetMapping("/{variantId}/{valueId}")
    public VariantOptionValue getById(@PathVariable Long variantId, @PathVariable Long valueId) {
        VariantOptionValueId id = new VariantOptionValueId(variantId, valueId);
        return service.getById(id);
    }

    // DELETE
    @DeleteMapping("/{variantId}/{valueId}")
    public String delete(@PathVariable Long variantId, @PathVariable Long valueId) {
        VariantOptionValueId id = new VariantOptionValueId(variantId, valueId);
        service.delete(id);
        return "Deleted successfully";
    }
}
