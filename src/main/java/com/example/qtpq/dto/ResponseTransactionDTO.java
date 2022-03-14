package com.example.qtpq.dto;

import com.example.qtpq.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseTransactionDTO {
    private double amount;
    private LocalDate dateCreate;
    private ResponseWalletDTO wallet;
    private ResponseOrderDTO orders;

    public ResponseTransactionDTO(Transaction transaction){
        this.amount = transaction.getAmount();
        this.dateCreate = transaction.getCreateDate();
        this.wallet = new ResponseWalletDTO(transaction.getWallet());
        this.orders = new ResponseOrderDTO(transaction.getOrders());
    }
}
