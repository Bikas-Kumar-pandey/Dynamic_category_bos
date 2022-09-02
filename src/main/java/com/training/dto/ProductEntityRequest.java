package com.training.dto;

import com.training.model.CategoryDocument;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductEntityRequest {
    private int productId;
    String brand;
    String model;
    String ram;
    String storage;

    private List<Object> features;
    private CategoryDocument.Camera camera;
}


