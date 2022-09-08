package com.training.service;

import com.training.config.CustomAggregationOperation;
import com.training.dto.FeedBackRequest;
import com.training.dto.FeedBackResponse;
import com.training.model.FeedBackDocument;
import com.training.model.ProductEntity;
import com.training.repository.CategoryDocumentRepository;
import com.training.repository.FeedBackDocumentRepository;
import com.training.repository.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeedBackDocumentService {


    private final FeedBackDocumentRepository feedBackDocumentRepository;

    private final ProductEntityRepository productEntityRepository;

    private final CategoryDocumentRepository categoryDocumentRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public FeedBackDocumentService(FeedBackDocumentRepository feedBackDocumentRepository, ProductEntityRepository productEntityRepository, CategoryDocumentRepository categoryDocumentRepository) {
        this.feedBackDocumentRepository = feedBackDocumentRepository;
        this.productEntityRepository = productEntityRepository;
        this.categoryDocumentRepository = categoryDocumentRepository;
    }

    public FeedBackDocument createFeedBack(FeedBackRequest request) throws Exception {
        if (!categoryDocumentRepository.existsByProductId(request.getProductId())) {
            throw new Exception("No Product by given id: " + request.getProductId());
        }
        Optional<ProductEntity> productById = productEntityRepository.findById(request.getProductId());
        if (productById.isEmpty()) {
            throw new Exception("No product by Id: " + request.getProductId());
        }
        FeedBackDocument feedBackDocument = new FeedBackDocument();
        feedBackDocument.setProductId(request.getProductId());
        feedBackDocument.setComment(request.getComment());
        feedBackDocument.setRatings(request.getRatings());

        FeedBackDocument save = feedBackDocumentRepository.save(feedBackDocument);
        return save;
    }

    public List<FeedBackDocument> getFeedBack(int productId) throws Exception {
        if (!categoryDocumentRepository.existsByProductId(productId)) {
            throw new Exception("No Product by given id: " + productId);
        }
        List<FeedBackDocument> feedbacks = feedBackDocumentRepository.findByProductId(productId);
        if (feedbacks.isEmpty()) {
            throw new Exception("No FeedBack by given product id: " + productId);
        }
        return feedbacks;
    }

    public List<FeedBackDocument> getFeedBacks() {
        // -------------------List Sort-------------------------------------
//        List<FeedBackDocument> all = feedBackDocumentRepository.findAll(Sort.by("ratings").descending());
//---------------------Only Sorting-------------------------------------------------
        Aggregation aggregation = Aggregation.newAggregation(FeedBackDocument.class, Aggregation.sort(Sort.by("ratings").ascending()));
//----------------------Only Grouping----------------------------------------
//        Aggregation aggregation = Aggregation.newAggregation(FeedBackDocument.class,
//                Aggregation.group("productId"));
//-------------------Sort And GRouping---------------------------------------
//        Aggregation aggregation = Aggregation.newAggregation(FeedBackDocument.class,Aggregation.sort(Sort.by("ratings").ascending()),
//              Aggregation.group("productId"));

        AggregationResults<FeedBackDocument> results = mongoTemplate.aggregate(aggregation, FeedBackDocument.class, FeedBackDocument.class);
        return results.getMappedResults();
    }


    public List<FeedBackResponse> joiningDocuments() {
        String lookup = "{ $lookup:{ from: 'Category', localField: 'productId',foreignField: 'productId',as: 'cat_feed'} }";
        List<CustomAggregationOperation> aggregationList = new ArrayList<>();
        aggregationList.add(new CustomAggregationOperation(lookup));

        TypedAggregation<FeedBackDocument> aggregation = Aggregation.newAggregation(FeedBackDocument.class, aggregationList);
        AggregationResults<FeedBackResponse> results = mongoTemplate.aggregate(aggregation, FeedBackResponse.class);
        return results.getMappedResults();
    }


    public List<FeedBackResponse> joiningDocumentsAndSorting() {
        String lookup = "{ $lookup:{ from: 'Category', localField: 'productId',foreignField: 'productId',as: 'cat_feed'} }";
        String sorting = "{ $sort:{productId:-1}} }";
        List<CustomAggregationOperation> aggregationList = new ArrayList<>();
        aggregationList.add(new CustomAggregationOperation(lookup));
        aggregationList.add(new CustomAggregationOperation(sorting));

        TypedAggregation<FeedBackDocument> aggregation = Aggregation.newAggregation(FeedBackDocument.class, aggregationList);
        AggregationResults<FeedBackResponse> results = mongoTemplate.aggregate(aggregation, FeedBackResponse.class);
        return results.getMappedResults();
    }

    public Page<FeedBackDocument> getProductInPage(int page, int size){
        Pageable pageable= PageRequest.of(page,size);
        Page<FeedBackDocument> all = feedBackDocumentRepository.findAll(pageable);
        return  all;

    }
}
