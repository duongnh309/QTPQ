package com.example.qtpq.dto;

import com.example.qtpq.model.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderDTO {
    private Long order_id;
    private String customerName;
    private String roomNumber;
    private LocalDate orderDate;
    private double totalPrice;
    private String phone;
    private String locationName;

    private Long menuId;
    private ResponseSellerDTO seller;
    private List<OrderDetailsDTO> orderDetails = new ArrayList<>();
    private TransactionDTO transaction;
    private String state ;

    public ResponseOrderDTO(Orders orders) {
        this.order_id = orders.getId();
        this.customerName = orders.getCustomerName();
        this.roomNumber = orders.getRoomNumber();
        this.orderDate = orders.getOrderDate();
        this.totalPrice = orders.getTotalPrice();
        this.phone = orders.getPhone();
        this.locationName = orders.getLocation().getName();
        this.menuId = orders.getMenu().getId();
        this.seller = new ResponseSellerDTO(orders.getSeller());
        for (int i = 0; i < orders.getOrderDetails().size(); i++) {
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(orders.getOrderDetails().get(i).getProduct().getId(), orders.getOrderDetails().get(i).getQuality());
            this.orderDetails.add(orderDetailsDTO);
        }
        this.transaction = new TransactionDTO(orders.getTransaction());
        this.state = orders.getState();
    }
}
