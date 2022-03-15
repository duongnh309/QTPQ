package com.example.qtpq.service;

import com.example.qtpq.dto.ResponseObject;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.message.request.CreateProductRequest;
import com.example.qtpq.model.Category;
import com.example.qtpq.model.Product;
import com.example.qtpq.repository.CategoryRepository;
import com.example.qtpq.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Transactional
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseObject createProduct(CreateProductRequest createProductRequest) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Category category = categoryRepository.findByCategoryName(createProductRequest.getCategories().getCategoryName());
            if(category.getCategoryName().isEmpty()){
                throw new IllegalStateException("This category does not exist");
            }

            log.debug("object {} ------", createProductRequest);
            Product createProduct = new Product(createProductRequest);
            createProduct.setCategories(category);
            Product savedProduct = productRepository.save(createProduct);

            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setData(savedProduct);
            return responseObject;
        } catch (Exception e) {
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            log.error("create error", e);
        }
        return responseObject;
    }

    public ResponseObject getList(int pageNum, int pageSize) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            Page<Product> productList = productRepository.findAll(pageable);


            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setData(productList);
        } catch (Exception e) {
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            log.error("findAll error {} ", e.getMessage());

        }
        return responseObject;
    }
    public ResponseObject update(Long id, CreateProductRequest createProductRequest){
        ResponseObject responseObject = new ResponseObject();
        Optional<Product> productOptional = productRepository.findById(id);
        if(!productOptional.isPresent()){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setData("This product does not exist");
            return responseObject;
        }
        Category category = categoryRepository.findByCategoryName(createProductRequest.getCategories().getCategoryName());
        if(category.getCategoryName().isEmpty()){
            throw new IllegalStateException("This category does not exist");
        }
        try{
            Product updatedProduct = productOptional.get();
            updatedProduct.setProductName(createProductRequest.getProductName());
            updatedProduct.setCategories(category);
            updatedProduct.setImgLink(createProductRequest.getImg());
            updatedProduct.setDescription(createProductRequest.getDescription());
            updatedProduct.setUnitPrice(createProductRequest.getUnitPrice());
            updatedProduct.setQuanlity(createProductRequest.getQuanlity());



            Product newProduct = productRepository.save(updatedProduct);
            responseObject.setData(newProduct);
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        }catch (Exception e){
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }
    public ResponseObject findById(Long id){
        ResponseObject responseObject = new ResponseObject();
        Optional<Product> productOptional = productRepository.findById(id);
        if(!productOptional.isPresent()){
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData("This product does not exist");
            return responseObject;
        }
        responseObject.setData(productOptional);
        responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
        return responseObject;
    }
    public ResponseObject findByName(String name, int pageNum, int pageSize){
        ResponseObject responseObject = new ResponseObject();
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Product> productFindByName = productRepository.findProductByProductName(name, pageable);
        if(productFindByName == null){
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData("This product does not exist");
            return responseObject;
        }
        responseObject.setData(productFindByName);
        responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
        return responseObject;
    }
}
