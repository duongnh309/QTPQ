package com.example.qtpq.controller;

import com.example.qtpq.dto.ResponseObject;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.model.PartnerType;
import com.example.qtpq.service.PartnerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/partnerType")
public class PartnerTypeController {

    @Autowired
    private PartnerTypeService service;
    @GetMapping("/getList")
    public ResponseEntity<ResponseObject> getListPartnerType(){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = service.getList();
        }catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseObject> getListPartnerTypeByID(@PathVariable("id") Long id){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = service.getListById(id);

        } catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @PostMapping("/createPartnerType")
    public ResponseEntity<ResponseObject> create(@RequestBody PartnerType pnType){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = service.create(pnType);

        } catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> update(@PathVariable("id") Long id, @RequestBody PartnerType pnType){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = service.update(pnType,id);

        } catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable("id") Long id){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = service.delete(id);

        } catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
