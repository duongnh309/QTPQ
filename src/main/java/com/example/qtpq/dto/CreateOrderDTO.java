package com.example.qtpq.dto;

import com.example.qtpq.model.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderDTO {
    private String customerName;
    private String roomNumber;
    private String phone;
    private String locationName;
    private Long sellerId;
    private List<OrderDetailsDTO> orderDetailsDTOS;
}
