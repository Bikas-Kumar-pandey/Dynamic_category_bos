package com.training.service;

import com.training.dto.ProductEntityRequest;
import com.training.dto.ProductEntityUpdate;
import com.training.dto.ProductResponse;
import com.training.model.CategoryDocument;
import com.training.model.ProductEntity;
import com.training.repository.CategoryDocumentRepository;
import com.training.repository.ProductEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductEntityService {
    private final ProductEntityRepository productRepo;
    private final CategoryDocumentRepository categoryDocumentRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ProductEntityService(ProductEntityRepository productRepo, CategoryDocumentRepository categoryDocumentRepository, ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.categoryDocumentRepository = categoryDocumentRepository;
        this.modelMapper = modelMapper;
    }

    public ProductResponse setProducts(ProductEntityRequest request) {
        ProductEntity saveProductEntity = modelMapper.map(request, ProductEntity.class);
        CategoryDocument categoryDoc = modelMapper.map(request, CategoryDocument.class);

        ProductEntity savedProduct = productRepo.save(saveProductEntity);

        categoryDoc.setProductId(savedProduct.getProduct_Id());

        CategoryDocument savedCategory = categoryDocumentRepository.save(categoryDoc);

        ProductResponse productResponse = new ProductResponse();

        modelMapper.map(savedCategory, productResponse);
        return productResponse;
    }

    public ProductResponse getProductById(int product_Id) throws Exception {
        Optional<CategoryDocument> categoryById = categoryDocumentRepository.findByProductId(product_Id);

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

    public String updateProduct(ProductEntityUpdate request, int product_Id) throws Exception {
        Optional<ProductEntity> productById = productRepo.findById(product_Id);
        Optional<CategoryDocument> categoryById = categoryDocumentRepository.findByProductId(product_Id);
        if (!productById.isPresent() || !categoryById.isPresent()) {
            throw new Exception("No Products present by given id " + product_Id);
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

    public String deleteById(int product_Id) throws Exception {
        Optional<ProductEntity> products = productRepo.findById(product_Id);
        Optional<CategoryDocument> category = categoryDocumentRepository.findByProductId(product_Id);
        if (products.isEmpty() || category.isEmpty()) {
            throw new Exception("No Products present by given id " + product_Id);
        }

        productRepo.deleteById(product_Id);
        categoryDocumentRepository.deleteByProductId(product_Id);
        return "Deleted Succesfully Product with given id: " + product_Id;
    }


}
