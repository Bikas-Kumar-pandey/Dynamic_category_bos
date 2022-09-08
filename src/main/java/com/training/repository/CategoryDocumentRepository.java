package com.training.repository;


import com.training.model.CategoryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryDocumentRepository extends MongoRepository<CategoryDocument,Integer> {
    Optional<CategoryDocument> findByProductId(int product_id);

    void deleteByProductId(int product_id);


    boolean existsByProductId(int productId);
}
