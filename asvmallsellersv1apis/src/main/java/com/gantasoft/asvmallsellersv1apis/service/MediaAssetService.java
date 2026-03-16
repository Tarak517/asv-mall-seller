package com.gantasoft.asvmallsellersv1apis.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gantasoft.asvmallsellersv1apis.entity.MediaAsset;
import com.gantasoft.asvmallsellersv1apis.enums.MediaOwnerType;
import com.gantasoft.asvmallsellersv1apis.repository.MediaAssetRepository;

@Service
public class MediaAssetService {

    private final MediaAssetRepository repo;
    private final Path uploadDir = Paths.get("uploads/media");

    public MediaAssetService(MediaAssetRepository repo) {
        this.repo = repo;
    }

    // ✅ Upload media
    public MediaAsset saveFile(MultipartFile file, Long productId) {
        try {
            Files.createDirectories(uploadDir);

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path target = uploadDir.resolve(fileName);
            Files.copy(file.getInputStream(), target);

            MediaAsset asset = new MediaAsset();
            asset.setOwnerType(MediaOwnerType.PRODUCT.getValue());
            asset.setOwnerId(productId);
            asset.setUrl("/uploads/media/" + fileName);
            asset.setContentType(file.getContentType());

            return repo.save(asset);

        } catch (Exception e) {
            throw new RuntimeException("Media upload failed", e);
        }
    }

    // ✅ Save via JSON (optional)
    public MediaAsset save(MediaAsset asset) {
        return repo.save(asset);
    }

    // ✅ Get single media
    public MediaAsset getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // ✅ Get all media
    public List<MediaAsset> getAll() {
        return repo.findAll();
    }

    // ✅ Delete media (DB + file)
    public void delete(Long id) {
        MediaAsset asset = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Media not found"));

        try {
            if (asset.getUrl() != null) {
                Path filePath = Paths.get(asset.getUrl().replace("/uploads/", "uploads/"));
                Files.deleteIfExists(filePath);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete media file", e);
        }

        repo.deleteById(id);
    }
}
