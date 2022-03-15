package com.example.qtpq.service;

import com.example.qtpq.dto.*;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.model.Transaction;
import com.example.qtpq.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseObject getByOrdersId(Long id){
        ResponseObject responseObject = new ResponseObject();
        try {
            Transaction transaction = transactionRepository.getTransactionsByOrdersId(id);

            responseObject.setData(new ResponseTransactionDTO(transaction));
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        }catch (Exception e){
            responseObject.setData(e.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
        }
        return responseObject;

    }
}
