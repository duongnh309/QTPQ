package com.example.qtpq.controller;

import com.example.qtpq.dto.CreateOrderDTO;
import com.example.qtpq.dto.ResponseObject;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/chekout")
    public ResponseEntity<ResponseObject> checkout(@RequestBody CreateOrderDTO createOrderDTO){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = orderService.createOrder(createOrderDTO);
        }catch (Exception e){
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping("/getListOrders")
    public ResponseEntity<ResponseObject> getList(){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = orderService.getListOrder();
        }catch (Exception e){
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping("/getOrdersById/{id}")
    public ResponseEntity<ResponseObject> getOrdersById(@PathVariable("id") Long id){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = orderService.getOrderById(id);
        }catch (Exception e){
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
