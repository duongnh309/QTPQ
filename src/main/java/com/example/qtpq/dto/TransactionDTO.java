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
public class TransactionDTO {
    private double amount;
    private LocalDate dateCreate;
    private WalletDTO wallet;

    public TransactionDTO(Transactions transactions){
        this.amount = transactions.getAmount();
        this.dateCreate = transactions.getCreateDate();
       this.wallet = new WalletDTO(transactions.getWallet());
    }
}
