package com.example.qtpq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

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
    @OneToOne(cascade = CascadeType.ALL)
    private Orders orders;

}
