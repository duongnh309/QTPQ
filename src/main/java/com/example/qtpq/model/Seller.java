package com.example.qtpq.model;

import com.example.qtpq.dto.SellerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "qr_code")
    private String qrCode;
    private String name;
    private String phone;
    private String mail;
    @Column(unique = true)
    private String username;
    private String password;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Menu menu;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Wallet wallet;
    //with order
    @OneToMany(mappedBy = "seller")
    private Set<Orders> orders;
    //with partner
    @ManyToOne
    @JoinColumn(name = "partner_id", nullable = false)
    private Partner partner;

    public Seller(SellerDTO seller) {
        this.qrCode = seller.getQrCode();
        this.name = seller.getName();
        this.phone = seller.getPhone();
        this.mail = seller.getMail();
        this.username = seller.getUsername();
        this.password = seller.getPassword();
    }
}
