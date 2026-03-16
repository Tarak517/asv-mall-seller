package com.gantasoft.asvmallsellersv1apis.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.gantasoft.asvmallsellersv1apis.entity.MediaAsset;
import com.gantasoft.asvmallsellersv1apis.service.MediaAssetService;

@RestController
@RequestMapping("/media-assets")
public class MediaAssetController {

    private final MediaAssetService service;

    public MediaAssetController(MediaAssetService service) {
        this.service = service;
    }

    // ✅ FIXED POST API
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MediaAsset uploadMedia(
            @RequestParam("file") MultipartFile file,
            @RequestParam("productId") Long productId
    ) {
        return service.saveFile(file, productId);
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
