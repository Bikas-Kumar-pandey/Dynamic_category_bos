//package com.training.repository;
//
//import com.training.dto.ProductEntityRequest;
//import com.training.model.CategoryDocument;
//import com.training.model.ProductEntity;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Collections;
//
////@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
////@RunWith(SpringRunner.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@SpringBootTest
//class ProductEntityRepositoryTest {
//
//    CategoryDocument.Camera camera;
//    @Autowired
//    private ProductEntityRepository productEntityRepository;
//    ;
//    @Autowired
//    private CategoryDocumentRepository categoryDocumentRepository;
//
//    @Test
//    public void ProductEntityRepoTest() {
//        ProductEntityRequest request = new ProductEntityRequest(1, "redmi", "note 3", "86", "4", Collections.singletonList("10px * 50px"), camera);
//        ProductEntity product = new ProductEntity(1, "redmi", "note 3", "86", "4");
//        CategoryDocument document = new CategoryDocument(1, "redmi", "note 3", "86", "4", "10px * 50px", camera);
//        CategoryDocument save = categoryDocumentRepository.save(document);
//        Assertions.assertThat(request).isEqualTo(document);
//// when(productEntityRepository.save(product)).thenReturn(product);
//
//
//    }
//}
//
