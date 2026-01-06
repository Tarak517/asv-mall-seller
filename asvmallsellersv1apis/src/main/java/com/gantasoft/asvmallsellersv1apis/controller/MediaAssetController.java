package com.gantasoft.asvmallsellersv1apis.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.gantasoft.asvmallsellersv1apis.entity.MediaAsset;
import com.gantasoft.asvmallsellersv1apis.service.MediaAssetService;

@RestController
@RequestMapping("/media-assets")
public class MediaAssetController {

    private final MediaAssetService service;

    public MediaAssetController(MediaAssetService service) {
        this.service = service;
    }
    

    @PostMapping
    public MediaAsset create(@RequestBody MediaAsset asset) {
        return service.save(asset);
    }
    

    @GetMapping("/{id}")
    public MediaAsset get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<MediaAsset> all() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
