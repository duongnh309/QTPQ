package com.example.qtpq.dto;

import com.example.qtpq.model.Wallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseWalletDTO {
    private double balance;
    private LocalDate createDate;
    private SellerDTO seller;
    private TransactionDTO transaction;

    public ResponseWalletDTO(Wallet wallet){
        this.balance = wallet.getBalance();
        this.createDate = wallet.getCreateDate();
        this.seller = new SellerDTO(wallet.getSeller());
        for (int i = 0; i < wallet.getTransactions().size(); i++) {
            this.transaction = new TransactionDTO(wallet.getTransactions().get(i));
        }
    }

}
