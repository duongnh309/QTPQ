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
public class TransactionDTO {
    private double amount;
    private LocalDate dateCreate;
    private WalletDTO wallet;

    public TransactionDTO(Transaction transaction){
        this.amount = transaction.getAmount();
        this.dateCreate = transaction.getCreateDate();
       this.wallet = new WalletDTO(transaction.getWallet());
    }
}
