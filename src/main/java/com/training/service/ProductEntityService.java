package com.training.service;

import com.training.dto.ProductEntityRequest;
import com.training.dto.ProductEntityUpdate;
import com.training.dto.ProductResponse;
import com.training.model.CategoryDocument;
import com.training.model.ProductEntity;
import com.training.repository.CategoryDocumentRepository;
import com.training.repository.ProductEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductEntityService {
    private final ProductEntityRepository productRepo;
    private final CategoryDocumentRepository categoryDocumentRepository;

    public ProductEntityService(ProductEntityRepository productRepo, CategoryDocumentRepository categoryDocumentRepository) {
        this.productRepo = productRepo;
        this.categoryDocumentRepository = categoryDocumentRepository;
    }

    public ProductResponse setProducts(ProductEntityRequest request) {
        ModelMapper modelMapper = new ModelMapper();

        ProductEntity saveProductEntity = modelMapper.map(request, ProductEntity.class);
        CategoryDocument categoryDoc = modelMapper.map(request, CategoryDocument.class);

        ProductEntity savedProduct = productRepo.save(saveProductEntity);

        categoryDoc.setProductId(savedProduct.getProductId());
        CategoryDocument savedCategory = categoryDocumentRepository.save(categoryDoc);

        ProductResponse productResponse = new ProductResponse();

        modelMapper.map(savedCategory, productResponse);
        return productResponse;
    }

    public ProductResponse getProductById(int productId) throws Exception {
        Optional<CategoryDocument> categoryById = categoryDocumentRepository.findByproductId(productId);

        if (categoryById.isEmpty()) {
            throw new Exception("Product By Given id is not present");
        }
        CategoryDocument category = categoryById.get();

        ModelMapper modelMapper = new ModelMapper();
        ProductResponse productResponse = new ProductResponse();
        modelMapper.map(category, productResponse);

        return productResponse;

    }

    public List<CategoryDocument> getAllProducts() {
        return categoryDocumentRepository.findAll();
    }

    public String updateProduct(ProductEntityUpdate request, int productId) throws Exception {
        Optional<ProductEntity> productById = productRepo.findById(productId);
        Optional<CategoryDocument> categoryById = categoryDocumentRepository.findByproductId(productId);
        if (!productById.isPresent() || !categoryById.isPresent()) {
            throw new Exception("No Products present by given id " + productId);
        }

        CategoryDocument categoryDocument = categoryById.get();
        ProductEntity product = productById.get();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(request, product);
        modelMapper.map(request, categoryDocument);

        productRepo.save(product);
        CategoryDocument save = categoryDocumentRepository.save(categoryDocument);
        System.out.println(save);

        return "saved";
    }

    public String deleteById(int productId) throws Exception {
        Optional<ProductEntity> products = productRepo.findById(productId);
        Optional<CategoryDocument> category = categoryDocumentRepository.findByproductId(productId);
        if (products.isEmpty() || category.isEmpty()) {
            throw new Exception("No Products present by given id " + productId);
        }

        productRepo.deleteById(productId);
        categoryDocumentRepository.deleteByProductId(productId);
        return "Deleted Succesfully Product with given id: " + productId;
    }
}
