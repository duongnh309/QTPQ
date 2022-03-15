package com.example.qtpq.dto;

import com.example.qtpq.model.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class SellerDTO {
    @NotNull(message = "username cant be null")
    private String username;
    @NotNull(message = "password cant be null")
    private String password;
    @NotNull(message = "QR cant be null")
    private String qrCode;
    @NotNull(message = "Name cant be null")
    private String name;
    @NotNull(message = "Phone cant be null")
    private String phone;
    @NotNull(message = "Mail cant be null")
    private String mail;
    @NotNull(message = "partner id cant be null")
    private Long partnerId;
    @NotNull(message = "Menu id cant be null")
    private Long menuId;



    public SellerDTO(Seller seller) {
        this.username = seller.getUsername();
        this.qrCode = seller.getQrCode();
        this.name = seller.getName();
        this.phone = seller.getPhone();
        this.mail = seller.getMail();
        this.partnerId = seller.getPartner().getId();
    }
}
