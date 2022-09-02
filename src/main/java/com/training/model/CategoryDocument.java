package com.training.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

@Document(collection = "Category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDocument {

    @Id
    private String id;

    @Column(name = "product_id")
    private int productId;

    private String brand;
    private String model;
    private String ram;
    private String storage;

    private List<Object> features;
    private Camera camera;


    @Data
    @NoArgsConstructor
    public static class Camera {
        private List<CameraFeatures> rareCamera;
        private List<CameraFeatures> backCamera;

    }


    @Data
    @NoArgsConstructor
    private static class CameraFeatures {
        private String pixels;
        private String camFeatures;

    }


}
