package com.example.qtpq.controller;

import com.example.qtpq.dto.ResponseObject;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping("/getWalletById/{id}")
    public ResponseEntity<ResponseObject> getWalletById(@PathVariable("id") Long id){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = walletService.getWalletById(id);
        } catch (Exception e){
            responseObject.setData(e.getMessage());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @PostMapping("/createWallet")
    public ResponseEntity<ResponseObject> createWallet(@RequestParam Long sellerId){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = walletService.createWallet(sellerId);
        }catch (Exception e){
            responseObject.setData(e.getMessage());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
