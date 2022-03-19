package com.example.qtpq.dto;

import com.example.qtpq.model.Transactions;
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

    public ResponseTransactionDTO(Transactions transactions){
        this.amount = transactions.getAmount();
        this.dateCreate = transactions.getCreateDate();
        this.wallet = new ResponseWalletDTO(transactions.getWallet());
        this.orders = new ResponseOrderDTO(transactions.getOrders());
    }
}
