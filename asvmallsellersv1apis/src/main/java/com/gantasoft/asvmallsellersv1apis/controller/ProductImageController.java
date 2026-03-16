package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.dto.ProductImageResponse;
import com.gantasoft.asvmallsellersv1apis.entity.Product;
import com.gantasoft.asvmallsellersv1apis.entity.ProductImage;
import com.gantasoft.asvmallsellersv1apis.service.ProductImageService;
import com.gantasoft.asvmallsellersv1apis.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product-images")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class ProductImageController {

    private final ProductImageService imageService;
    private final ProductService productService;

    public ProductImageController(ProductImageService imageService,
                                  ProductService productService) {
        this.imageService = imageService;
        this.productService = productService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductImageResponse> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("productId") Long productId
    ) {

        Product product = productService.get(productId);

        ProductImage saved = imageService.saveImage(product, file);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(map(saved));
    }

    @GetMapping("/product/{productId}")
    public List<ProductImageResponse> getImages(@PathVariable Long productId) {

        return imageService.getByProductId(productId)
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> serveFile(
            @PathVariable String filename,
            HttpServletRequest request
    ) {

        Resource resource = imageService.loadFile(filename);

        String type = "application/octet-stream";

        try {
            type = request.getServletContext()
                    .getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ignored) {
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(type))
                .body(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        imageService.delete(id);

        return ResponseEntity.noContent().build();
    }

    private ProductImageResponse map(ProductImage img) {

        return new ProductImageResponse(
                img.getImageId(),
                img.getProduct().getProductId(),
                "http://localhost:9020/product-images/files/" + img.getUrl(),
                img.getIsPrimary(),
                img.getSortOrder(),
                img.getCreatedAt(),
                img.getUpdatedAt()
        );
    }
}