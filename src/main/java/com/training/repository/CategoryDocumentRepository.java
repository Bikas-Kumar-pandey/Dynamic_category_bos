package com.training.repository;

import com.training.dto.ProductResponses;
import com.training.model.CategoryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryDocumentRepository extends MongoRepository<CategoryDocument,Integer> {
    Optional<CategoryDocument> findByproductId(int productId);

    void deleteByProductId(int productId);

//    @Query("db.demo")
//    @Query("db.product_feature.aggregate([{$project:{_id:0,productId:0,_class:0}}])")
//
//    List<ProductResponses> getAllProducts();
}
