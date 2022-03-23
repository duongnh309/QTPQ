package com.example.qtpq.service;

import com.example.qtpq.dto.ResponseObject;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.model.Category;
import com.example.qtpq.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseObject getAll() {
        ResponseObject responseObject = new ResponseObject();
        List<Category> categories = categoryRepository.findAll();
        responseObject.setData(categories);
        responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
        return responseObject;
    }
    public ResponseObject createCategory(String  categoryName){
        ResponseObject responseObject = new ResponseObject();
        if(categoryName.isEmpty()){
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData("Please input category name");
            return responseObject;
        }
        Category category = new Category();
        category.setCategoryName(categoryName);
        Category savedCate = categoryRepository.save(category);
        responseObject.setData(savedCate);
        responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
        responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        return responseObject;
    }
    public ResponseObject editCategory(Long id, String categoryName){
        ResponseObject responseObject = new ResponseObject();
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(!optionalCategory.isPresent()){
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData("This category does not exist");
            return responseObject;
        }
        else if(categoryName.isEmpty()){
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData("Please input category name");
            return responseObject;
        }
        try{
            optionalCategory.get().setCategoryName(categoryName);
            Category savedCate = categoryRepository.save(optionalCategory.get());
            responseObject.setData(savedCate);
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        }catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return responseObject;
        }
        return responseObject;
    }
    public ResponseObject findById(Long id){
        ResponseObject responseObject = new ResponseObject();
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(!optionalCategory.isPresent()){
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData("This category does not exist");
            return responseObject;
        }
        responseObject.setData(optionalCategory.get());
        responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
        return responseObject;
    }
}
