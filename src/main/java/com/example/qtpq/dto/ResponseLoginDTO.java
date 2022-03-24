package com.example.qtpq.dto;

import com.example.qtpq.model.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ResponseLoginDTO {
    private Long sellerId;
    private Long menuId;
    private String sellerName;
    private String username;
    private String phone;
    private String email;
    private String address;

    public ResponseLoginDTO(Seller seller){
        this.sellerId = seller.getId();
        this.menuId = seller.getMenu().getId();
        this.sellerName = seller.getName();
        this.phone = seller.getPhone();
        this.email = seller.getMail();
        this.username = seller.getUsername();
        this.address = "Phu Quoc";
    }
}
