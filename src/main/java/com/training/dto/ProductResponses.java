package com.training.dto;

import com.training.model.CategoryDocument;

import java.util.List;

public interface ProductResponses {
    int productId();

    String brand();

    String model();

    String ram();

    String storage();

    List<Object> features();

    CategoryDocument.Camera camera();


}
