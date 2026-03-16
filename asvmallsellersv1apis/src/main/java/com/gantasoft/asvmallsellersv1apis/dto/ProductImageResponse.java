package com.gantasoft.asvmallsellersv1apis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageResponse {

    private Long imageId;
    private Long productId;
    private String url;
    private Boolean isPrimary;
    private Integer sortOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}