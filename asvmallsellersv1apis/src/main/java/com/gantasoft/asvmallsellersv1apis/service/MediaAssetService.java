package com.gantasoft.asvmallsellersv1apis.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.gantasoft.asvmallsellersv1apis.entity.MediaAsset;
import com.gantasoft.asvmallsellersv1apis.repository.MediaAssetRepository;

@Service
public class MediaAssetService {

    private final MediaAssetRepository repo;

    public MediaAssetService(MediaAssetRepository repo) {
        this.repo = repo;
    }

    public MediaAsset save(MediaAsset asset) {
        return repo.save(asset);
    }

    public MediaAsset getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<MediaAsset> getAll() {
        return repo.findAll();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
