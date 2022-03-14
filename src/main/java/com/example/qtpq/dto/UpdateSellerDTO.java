package com.example.qtpq.dto;

import com.example.qtpq.model.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Valid
@Data
@AllArgsConstructor
public class UpdateSellerDTO {
    private String password;
    private String qrCode;
    private String name;
    private String phone;
    private String mail;
    private Long partnerId;


    public UpdateSellerDTO(Seller seller) {
        this.password = seller.getPassword();
        this.qrCode = seller.getQrCode();
        this.name = seller.getName();
        this.phone = seller.getPhone();
        this.mail = seller.getMail();
    }
}
