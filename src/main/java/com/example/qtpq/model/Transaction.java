package com.example.qtpq.model;

import com.example.qtpq.dto.ResponseTransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    @Column(name = "create_date")
    private LocalDate createDate;
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Orders orders;



}
