package com.training.controller;


import com.training.dto.FeedBackRequest;
import com.training.dto.FeedBackResponse;
import com.training.model.FeedBackDocument;
import com.training.service.FeedBackDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedBackDocumentController {

    @Autowired
    private final FeedBackDocumentService feedBackDocumentService;


    public FeedBackDocumentController(FeedBackDocumentService feedBackDocumentService) {
        this.feedBackDocumentService = feedBackDocumentService;
    }

    @PostMapping
    public FeedBackDocument createFeedBack(@RequestBody FeedBackRequest request) throws Exception {
        return feedBackDocumentService.createFeedBack(request);
    }

    @GetMapping("{productId}")
    public List<FeedBackDocument> getFeedBack(@PathVariable int productId) throws Exception {
        return feedBackDocumentService.getFeedBack(productId);
    }

    //-----------SORTING AND GROUPING----------------------------------------------------
    @GetMapping
    public List<FeedBackDocument> getFeedBacks() throws Exception {
        return feedBackDocumentService.getFeedBacks();
    }

    //-----------------------JOINING DDOCUMENTS--------------------------------------------
    @GetMapping("/documents")
    public List<FeedBackResponse> joiningDocuments() {
        return feedBackDocumentService.joiningDocuments();
    }

    @GetMapping("/documentsAndSorting")
    public List<FeedBackResponse> joiningDocumentsAndSorting() {
        return feedBackDocumentService.joiningDocumentsAndSorting();
    }

    @GetMapping("/page")
    public Page<FeedBackDocument> getProductInPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        return feedBackDocumentService.getProductInPage(page, size);
    }
}
