package com.example.qtpq.controller;

import com.example.qtpq.dto.ResponseObject;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/getByOrdersId/{id}")
    public ResponseEntity<ResponseObject> getByOrdersId(@PathVariable("id") Long id){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = transactionService.getByOrdersId(id);
        }catch (Exception e){
            responseObject.setData(e.getMessage());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
