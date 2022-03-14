package com.example.qtpq.service;

import com.example.qtpq.dto.ResponseObject;
import com.example.qtpq.dto.ResponseWalletDTO;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.model.Seller;
import com.example.qtpq.model.Wallet;
import com.example.qtpq.repository.SellerRepository;
import com.example.qtpq.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;
    private SellerRepository sellerRepository;
    public ResponseObject createWallet(Long sellerId){
        ResponseObject responseObject = new ResponseObject();
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(() -> {
            throw new IllegalStateException("This seller does not exist");
        });
        LocalDate date = LocalDate.now();
        try {
            Wallet wallet = Wallet.builder()
                    .balance(0)
                    .createDate(date)
                    .seller(seller)
                    .build();
            Wallet savedWallet = walletRepository.save(wallet);
            seller.setWallet(savedWallet);
            sellerRepository.save(seller);
            responseObject.setData(new ResponseWalletDTO(savedWallet));
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        }catch (Exception e){
            responseObject.setData(e.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
        }
        return responseObject;
    }
    public ResponseObject getWalletById(Long Id){
        ResponseObject responseObject = new ResponseObject();
        Wallet wallet = walletRepository.findById(Id).orElseThrow(() -> {
            throw new IllegalStateException("This wallet does not exist");
        });
        try {
            responseObject.setData(new ResponseWalletDTO(wallet));
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        }catch (Exception e){
            responseObject.setData(e.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
        }
        return responseObject;
    }
}
