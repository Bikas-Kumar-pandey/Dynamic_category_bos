package com.training.controller;

import com.training.dto.ProductEntityRequest;
import com.training.dto.ProductEntityUpdate;
import com.training.dto.ProductResponse;
import com.training.model.CategoryDocument;
import com.training.service.ProductEntityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductEntityController {

    private final ProductEntityService productEntityService;

    public ProductEntityController(ProductEntityService productEntityService) {
        this.productEntityService = productEntityService;
    }

    @PostMapping
    public ProductResponse setProducts(@RequestBody ProductEntityRequest request) {
        return productEntityService.setProducts(request);
    }

    @GetMapping("/{productId}")
    public ProductResponse getProductById(@PathVariable int productId) throws Exception {
        return productEntityService.getProductById(productId);
    }


    @GetMapping
    public List<CategoryDocument> getProducts() {
        return productEntityService.getAllProducts();
    }

    @PutMapping("/{productId}")
    public String updateProduct(@RequestBody ProductEntityUpdate request, @PathVariable int productId) throws Exception {
        return productEntityService.updateProduct(request, productId);
    }

    @DeleteMapping("/{productId}")
    public String deleteById(@PathVariable int productId) throws Exception {
        return productEntityService.deleteById(productId);
    }


}
