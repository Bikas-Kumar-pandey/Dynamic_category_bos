package com.training.dto;

import com.training.model.CategoryDocument;
import lombok.Data;

import java.util.List;

@Data
public class FeedBackResponse {
    private int productId;
    private String comment;
    private double ratings;

    List<CategoryDocument> cat_feed;
//    String brand;
//    String model;
//    String ram;
//    String storage;
//    private List<Object> features;
//    private CategoryDocument.Camera camera;


}
