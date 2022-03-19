package com.example.qtpq.service;

import com.example.qtpq.dto.*;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.model.Transactions;
import com.example.qtpq.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseObject getByOrdersId(Long id){
        ResponseObject responseObject = new ResponseObject();
        try {
            Transactions transactions = transactionRepository.getTransactionsByOrdersId(id);

            responseObject.setData(new ResponseTransactionDTO(transactions));
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
