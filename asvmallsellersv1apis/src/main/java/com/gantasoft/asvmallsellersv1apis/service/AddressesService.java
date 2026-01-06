package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.Addresses;
import com.gantasoft.asvmallsellersv1apis.repository.AddressesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AddressesService {

    private final AddressesRepository repository;

    public AddressesService(AddressesRepository repository) {
        this.repository = repository;
    }

    public List<Addresses> getAll() {
        return repository.findAll();
    }

    public Optional<Addresses> getById(Long id) {
        return repository.findById(id);
    }

    public Addresses save(Addresses address) {
        return repository.save(address);
    }

    public Addresses update(Long id, Addresses address) {
        return repository.findById(id).map(existing -> {
            existing.setFullName(address.getFullName());
            existing.setPhone(address.getPhone());
            existing.setLine1(address.getLine1());
            existing.setLine2(address.getLine2());
            existing.setCity(address.getCity());
            existing.setState(address.getState());
            existing.setCountry(address.getCountry());
            existing.setPincode(address.getPincode());
            existing.setGeoLat(address.getGeoLat());
            existing.setGeoLng(address.getGeoLng());
            existing.setUpdatedAt(LocalDateTime.now());
            return repository.save(existing);
            
        }).orElseThrow(() -> new RuntimeException("Address not found with id " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
