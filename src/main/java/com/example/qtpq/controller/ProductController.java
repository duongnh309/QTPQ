package com.example.qtpq.controller;

import com.example.qtpq.dto.ResponseObject;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.message.request.CreateProductRequest;
import com.example.qtpq.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product/getList")
    public ResponseEntity<ResponseObject> getList(@RequestParam int pageNum, @RequestParam int pageSize) {
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = productService.getList(pageNum, pageSize);
        } catch (Exception e) {
            log.error("create error {}", e.getMessage());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/product/create")
    public ResponseEntity<ResponseObject> create(@RequestBody CreateProductRequest createProduct) {
        log.info("create form {}", createProduct.getProductName());
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = productService.createProduct(createProduct);
        } catch (Exception e) {
            log.error("create error {}", e.getMessage());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
        }
        log.debug("Create response {}", responseObject);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PutMapping("/product/update")
    public ResponseEntity<ResponseObject> update(@RequestParam("productID") Long id, @RequestBody CreateProductRequest request) {
        ResponseObject responseObject = new ResponseObject();
        if (id == null) {
            responseObject.setData("ID can't be null");
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        try {
            responseObject = productService.update(id, request);

        } catch (Exception e) {
            log.error(e.getMessage());
            responseObject.setData(e.getMessage());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/product/findById/{id}")
    public ResponseEntity<ResponseObject> findProductById(@PathVariable("id") Long id) {
        ResponseObject responseObject = new ResponseObject();
        if (id == null) {
            responseObject.setData("ID can't be null");
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        try {
            responseObject = productService.findById(id);

        } catch (Exception e) {
            log.error(e.getMessage());
            responseObject.setData(e.getMessage());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/product/findByName")
    public ResponseEntity<ResponseObject> findProductByName(@RequestParam("productName") String name,
                                                            @RequestParam int pageNum,
                                                            @RequestParam int pageSize) {
        ResponseObject responseObject = new ResponseObject();
        if (name.isEmpty()) {
            responseObject.setData("Name can't be null");
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        try {
            responseObject = productService.findByName(name, pageNum, pageSize);

        } catch (Exception e) {
            log.error(e.getMessage());
            responseObject.setData(e.getMessage());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
