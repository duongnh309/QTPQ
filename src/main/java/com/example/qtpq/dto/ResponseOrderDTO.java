package com.example.qtpq.dto;

import com.example.qtpq.model.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderDTO {
    private String customerName;
    private String roomNumber;
    private LocalDate orderDate;
    private double totalPrice;
    private String phone;
    private String locationName;

    private Long menuId;
    private ResponseSellerDTO seller;
    private OrderDetailsDTO orderDetails;
    private TransactionDTO transaction;

    public ResponseOrderDTO(Orders orders) {
        this.customerName = orders.getCustomerName();
        this.roomNumber = orders.getRoomNumber();
        this.orderDate = orders.getOrderDate();
        this.totalPrice = orders.getTotalPrice();
        this.phone = orders.getPhone();
        this.locationName = orders.getLocation().getName();
        this.menuId = orders.getMenu().getId();
        this.seller = new ResponseSellerDTO(orders.getSeller());
        for (int i = 0; i < orders.getOrderDetails().size(); i++) {
            this.orderDetails = new OrderDetailsDTO(orders.getOrderDetails().get(i).getProduct().getId(), orders.getOrderDetails().get(i).getQuality());
        }
        this.transaction = new TransactionDTO(orders.getTransaction());
    }
}
