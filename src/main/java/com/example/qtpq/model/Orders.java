package com.example.qtpq.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "room_number")
    private String roomNumber;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "total_price")
    private double totalPrice;
    private String phone;
    @ManyToOne(cascade = CascadeType.ALL)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;
    //with order detail
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
    //with transaction
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "orders")
    private Transaction transaction;
}
