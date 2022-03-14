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
public class WalletDTO {
    private double balance;
    private LocalDate createDate;
    private SellerDTO seller;

    public WalletDTO(Wallet wallet){
        this.balance = wallet.getBalance();
        this.createDate = wallet.getCreateDate();
        this.seller = new SellerDTO(wallet.getSeller());
    }
}
