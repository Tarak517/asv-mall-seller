package com.gantasoft.asvmallsellersv1apis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "file.upload")
@Data
public class FileStorageProperties {
    private String uploadDir = "uploads/";
    private long maxFileSize = 5242880; // 5MB default
    private List<String> allowedTypes = List.of("image/jpeg", "image/png", "image/jpg", "image/webp");
}
