package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.Taxes;
import com.gantasoft.asvmallsellersv1apis.service.TaxesService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/taxes")
public class TaxesController {

    private final TaxesService service;

    public TaxesController(TaxesService service) {
        this.service = service;
    }

    @GetMapping
    public List<Taxes> getAllTaxes() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Taxes getTax(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Taxes createTax(@RequestBody Taxes tax) {
        return service.save(tax);
    }

    @PutMapping("/{id}")
    public Taxes updateTax(@PathVariable Long id, @RequestBody Taxes tax) {
        Taxes existing = service.getById(id);
        if (existing != null) {
            existing.setName(tax.getName());
            existing.setRatePercent(tax.getRatePercent());
            existing.setIsInclusive(tax.getIsInclusive());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTax(@PathVariable Long id) {
        service.delete(id);
    }
}
