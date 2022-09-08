package com.training.repository;

import com.training.model.FeedBackDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface FeedBackDocumentRepository extends MongoRepository<FeedBackDocument, String> {

//    @Aggregation(pipeline = {"{$group:{_id:'$brand',totalProduct:{$sum:1}}}"
//            ,"{$project:{_id:0,brand:'$_id',totalProduct:1}}"
//            ,"{$sort:{brand:1}}"})
    List<FeedBackDocument> findByProductId(int productId);

}
