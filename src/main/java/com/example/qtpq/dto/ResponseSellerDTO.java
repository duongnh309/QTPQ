package com.example.qtpq.dto;

import com.example.qtpq.model.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ResponseSellerDTO {
    private String username;
    private String qrCode;
    private String name;
    private String phone;
    private String mail;
    private PartnerDTO partner;
    private MenuDTO menu;


    public ResponseSellerDTO(Seller seller) {
        this.username = seller.getUsername();
        this.qrCode = seller.getQrCode();
        this.name = seller.getName();
        this.phone = seller.getPhone();
        this.mail = seller.getMail();
        this.partner = new PartnerDTO(seller.getPartner());
        this.menu = new MenuDTO(seller.getMenu());
    }
}
