package com.training.dto;

import com.training.model.CategoryDocument;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductResponse {
    private int productId;
    String brand;
    String model;
    String ram;
    String storage;

    private List<Object> features;
    private CategoryDocument.Camera camera;
}
