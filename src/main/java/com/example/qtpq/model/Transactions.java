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
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    @Column(name = "create_date")
    private LocalDate createDate;


    @ManyToOne
    private Wallet wallet;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Orders orders;



}
