package com.example.qtpq.controller;

import com.example.qtpq.dto.ResponseObject;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/catefory/create")
    public ResponseEntity<ResponseObject> createCategory(@RequestParam("CategoryName") String name){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = categoryService.createCategory(name);
        }catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @PutMapping("/category/update/{id}")
    public ResponseEntity<ResponseObject> updateCategory(@PathVariable("id") Long id, @RequestParam("CategoryName") String name ){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = categoryService.editCategory(id, name);
        }catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping("/category/getById/{id}")
    public ResponseEntity<ResponseObject> getByID(@PathVariable("id") Long id){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = categoryService.findById(id);
        }catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}

