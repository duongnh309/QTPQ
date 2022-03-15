package com.example.qtpq.model;

import com.example.qtpq.dto.ResponseWalletDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Builder
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private Long walletId;
    private double balance;
    @Column(name = "create_date")
    private LocalDate createDate;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;
    @OneToMany(mappedBy = "wallet")
    private List<Transaction> transactions ;

    public Wallet(ResponseWalletDTO responseWalletDTO) {
        this.balance = responseWalletDTO.getBalance();
        this.createDate = responseWalletDTO.getCreateDate();
        this.seller = new Seller(responseWalletDTO.getSeller());
    }
    public Wallet(List<Transaction> transactions){
        for (int i = 0; i < transactions.size(); i++) {
            this.transactions.add(transactions.get(i));
        }
    }
}
