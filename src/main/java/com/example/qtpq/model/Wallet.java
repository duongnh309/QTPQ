package com.example.qtpq.model;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;
    @OneToMany(mappedBy = "wallet")
    private List<Transaction> transactions;

}
