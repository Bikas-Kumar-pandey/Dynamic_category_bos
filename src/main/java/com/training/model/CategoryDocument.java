package com.training.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

@Document(collection = "Category")
@Data
@NoArgsConstructor
//@AllArgsConstructor

public class CategoryDocument {

    @Id
    private String id;

    private int productId;

    private String brand;
    private String model;
    private String ram;
    private String storage;

    private List<Object> features;

    public CategoryDocument(String id, int product_Id, String brand, String model, String ram, String storage, List<Object> features, Camera camera) {
        this.id = id;
        this.productId = product_Id;
        this.brand = brand;
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.features = features;
        this.camera = camera;
    }

    private Camera camera;




    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Camera {
        private List<CameraFeatures> rareCamera;
        private List<CameraFeatures> backCamera;

//        public void getRareCamera(List<CameraFeatures> cameraSide) {
//        }
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CameraFeatures {
        private String pixels;
        private String camFeatures;

    }


}
