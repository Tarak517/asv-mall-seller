package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.SellerPayout;
import com.gantasoft.asvmallsellersv1apis.service.SellerPayoutService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/seller-payouts")
public class SellerPayoutController {

    private final SellerPayoutService service;

    public SellerPayoutController(SellerPayoutService service) {
        this.service = service;
    }

    @GetMapping
    public List<SellerPayout> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SellerPayout getById(@PathVariable Long id) {
        return service.getById(id);
    }

    
    @PostMapping
    public List<SellerPayout> create(@RequestBody Object payload) {
        if (payload instanceof List<?> list) {
            
            return list.stream()
                       .map(item -> service.save(convertToPayout(item)))
                       .collect(Collectors.toList());
        } else {
           
            return List.of(service.save(convertToPayout(payload)));
        }
    }

    @PutMapping("/{id}")
    public SellerPayout update(@PathVariable Long id, @RequestBody SellerPayout payout) {
        SellerPayout existing = service.getById(id);
        if (existing != null) {
            existing.setSellerId(payout.getSellerId());
            existing.setAmount(payout.getAmount());
            existing.setCurrency(payout.getCurrency());
            existing.setStatus(payout.getStatus());
            existing.setPaidAt(payout.getPaidAt());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


    private SellerPayout convertToPayout(Object obj) {
       
        return new com.fasterxml.jackson.databind.ObjectMapper()
                .convertValue(obj, SellerPayout.class);
    }
}
