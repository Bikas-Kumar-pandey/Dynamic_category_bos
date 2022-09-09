package com.training.service;

import com.training.dto.ProductEntityRequest;
import com.training.dto.ProductResponse;
import com.training.model.CategoryDocument;
import com.training.repository.CategoryDocumentRepository;
import com.training.repository.ProductEntityRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@SpringBootTest
//@ExtendWith(MockitoExtension.class)
public class ProductEntityServiceTest {
    CategoryDocument.Camera camera = new CategoryDocument.Camera();
    ProductEntityRequest productEntityRequest;

    @Autowired
    ModelMapper modelMapper;
    @InjectMocks
    private ProductEntityService productEntityService;
    @Mock
    private CategoryDocumentRepository categoryDocumentRepository;

//    public ProductEntityServiceTest(ProductEntityRepository productEntityRepository) {
//        this.productEntityRepository = productEntityRepository;
//    }

    @Mock
    private ProductEntityRepository productEntityRepository;

    @Before
    public void setUp() {
        productEntityService = new ProductEntityService(productEntityRepository, categoryDocumentRepository, modelMapper);
        CategoryDocument.CameraFeatures cameraFeatures = new CategoryDocument.CameraFeatures("8 Mp", "Super sonic");
        ArrayList list = new ArrayList<>();
        list.add(cameraFeatures);
        camera.setBackCamera(list);
        camera.setRareCamera(list);
            }

//    @Test
//    public void setProductsTest() {
////        productEntityService = new ProductEntityService(productEntityRepository, categoryDocumentRepository, modelMapper);
//        ;
////        categoryDocument = new CategoryDocument("1", 1, "redmi", "note 3", "4", "86", Collections.singletonList("10px * 50px"), camera);
//        productEntityRequest = new ProductEntityRequest("redmi", "note 3", "4", "86", Collections.singletonList("10px * 50px"), camera);
//
//        ProductResponse actualResponse = productEntityService.setProducts(productEntityRequest);
//        System.out.println(actualResponse);
//
//        Assertions.assertThat(actualResponse.getBrand()).isNull();;
////        Assertions.assertThat(actualResponse).isEqualTo(productEntityRequest);
////        Assertions.assertThat(categoryDocument).isEqualTo(productResponse);
//    }

    @Test
    public void setProductsTests() {
//        productEntityService = new ProductEntityService(productEntityRepository, categoryDocumentRepository, modelMapper);
        ;
//        categoryDocument = new CategoryDocument("1", 1, "redmi", "note 3", "4", "86", Collections.singletonList("10px * 50px"), camera);
        productEntityRequest = new ProductEntityRequest("redmi", "note 3", "4", "86", Collections.singletonList("10px * 50px"), camera);

        ProductEntityRequest actualResponse = productEntityService.setProductss(productEntityRequest);
        System.out.println(actualResponse);

        Assertions.assertThat(actualResponse.getBrand()).isNull();;
//        Assertions.assertThat(actualResponse).isEqualTo(productEntityRequest);
//        Assertions.assertThat(categoryDocument).isEqualTo(productResponse);
    }
}