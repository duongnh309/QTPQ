package com.example.qtpq.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Builder
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    private int quality;
    //with order
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;
    //Ralation with product
    @ManyToOne
    private Product product;

}
