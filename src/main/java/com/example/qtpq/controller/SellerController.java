package com.example.qtpq.controller;

import com.example.qtpq.dto.*;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;
    @PostMapping("/createSeller")
    public ResponseEntity<ResponseObject> createSeller(@RequestBody SellerDTO sellerDTO){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = sellerService.createSeller(sellerDTO);
        }catch (Exception e){
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping("/getListSeller")
    public ResponseEntity<ResponseObject> getList(){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = sellerService.getListSeller();

        } catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping("/getSellerById/{id}")
    public ResponseEntity<ResponseObject> getSellerById(@PathVariable("id") Long id){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = sellerService.getSellerById(id);
        }catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PutMapping("/updateSeller/{id}")
    public ResponseEntity<ResponseObject> updateSeller(@PathVariable("id") Long id, @RequestBody UpdateSellerDTO sellerDTO){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = sellerService.updateSeller(id, sellerDTO);
        }
        catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @DeleteMapping("/deleteSeller/{id}")
    public ResponseEntity<ResponseObject> deleteSeller(@PathVariable("id") Long id){
        ResponseObject responseObject = new ResponseObject();
        try {
           responseObject = sellerService.deleteSeller(id);
        }
        catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody LoginDTO loginDTO){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = sellerService.login(loginDTO);
        }
        catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
