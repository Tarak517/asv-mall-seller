package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.StoreAddress;
import com.gantasoft.asvmallsellersv1apis.entity.StoreAddressId;
import com.gantasoft.asvmallsellersv1apis.service.StoreAddressService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/store-addresses")
public class StoreAddressController {

    private final StoreAddressService service;

    public StoreAddressController(StoreAddressService service) {
        this.service = service;
    }

    @GetMapping
    public List<StoreAddress> getAll() {
        return service.getAll();
    }

    @GetMapping("/{storeId}/{addressId}")
    public StoreAddress getById(@PathVariable Long storeId, @PathVariable Long addressId) {
        return service.getById(new StoreAddressId(storeId, addressId));
    }

    @PostMapping
    public StoreAddress create(@RequestBody StoreAddress storeAddress) {
        return service.save(storeAddress);
    }

    @PutMapping("/{storeId}/{addressId}")
    public StoreAddress update(@PathVariable Long storeId,
                               @PathVariable Long addressId,
                               @RequestBody StoreAddress storeAddress) {
        StoreAddress existing = service.getById(new StoreAddressId(storeId, addressId));
        if (existing != null) {
            existing.setType(storeAddress.getType());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{storeId}/{addressId}")
    public void delete(@PathVariable Long storeId, @PathVariable Long addressId) {
        service.delete(new StoreAddressId(storeId, addressId));
    }
}
