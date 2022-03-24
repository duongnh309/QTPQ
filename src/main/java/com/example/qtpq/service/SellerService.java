package com.example.qtpq.service;

import com.example.qtpq.dto.*;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.model.Menu;
import com.example.qtpq.model.Partner;
import com.example.qtpq.model.Seller;
import com.example.qtpq.repository.MenuRepository;
import com.example.qtpq.repository.PartnerInMenuRepository;
import com.example.qtpq.repository.PartnerRepository;
import com.example.qtpq.repository.SellerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    private PartnerRepository partnerRepository;

    private MenuRepository menuRepository;

    private PartnerInMenuRepository partnerInMenuRepository;

    public ResponseObject createSeller(SellerDTO sellerDTO) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Partner partner = partnerRepository.findById(sellerDTO.getPartnerId()).orElseThrow(() -> {
                throw new IllegalStateException("This partner does not exist");
            });
            Menu menu = menuRepository.findById(sellerDTO.getMenuId()).orElseThrow(() -> {
                throw new IllegalStateException("This Menu does not exist");
            });

            if (partnerInMenuRepository.isInPartner(sellerDTO.getMenuId(), sellerDTO.getPartnerId()) != 1){
                throw new IllegalStateException("This partner does not have menu " + menu.getName());
            }

            Seller seller = new Seller(sellerDTO);
            seller.setPartner(partner);
            seller.setMenu(menu);


            Seller savedSeller = sellerRepository.save(seller);
            responseObject.setData(new ResponseSellerDTO(savedSeller));
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }

    public ResponseObject updateSeller(Long id, UpdateSellerDTO sellerDTO) {
        ResponseObject responseObject = new ResponseObject();
        Seller seller = sellerRepository.findById(id).orElseThrow(() -> {
            throw new IllegalStateException("This seller does not exist");
        });
        try {

            if (!sellerDTO.getName().isEmpty()) {
                seller.setName(sellerDTO.getName());
            }
            if (!sellerDTO.getPhone().isEmpty()) {
                seller.setPhone(sellerDTO.getPhone());
            }
            if (!sellerDTO.getMail().isEmpty()) {
                seller.setMail(sellerDTO.getMail());
            }


            Seller savedSeller = sellerRepository.save(seller);
            responseObject.setData(new ResponseSellerDTO(savedSeller));
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }

    public ResponseObject getListSeller() {
        ResponseObject responseObject = new ResponseObject();
        try {
            List<Seller> listSeller = sellerRepository.findAll();
            List<ResponseSellerDTO> listDTO = new ArrayList<>();
            for (Seller seller : listSeller) {
                ResponseSellerDTO responseSellerDTO = new ResponseSellerDTO(seller);
                listDTO.add(responseSellerDTO);
            }
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setData(listDTO);
        } catch (Exception e) {
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            log.error("findAll Seller error {} ", e.getMessage());
        }
        return responseObject;
    }

    public ResponseObject getSellerById(Long id) {
        ResponseObject responseObject = new ResponseObject();
        Seller seller = sellerRepository.findById(id).get();
        ResponseSellerDTO sellerDTO = new ResponseSellerDTO(seller);
        if (sellerDTO == null) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData("This seller does not exist");
            return responseObject;
        }
        responseObject.setData(new ResponseSellerDTO(seller));
        responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
        return responseObject;
    }

    public ResponseObject deleteSeller(Long id) {
        ResponseObject responseObject = new ResponseObject();


        try {
            sellerRepository.findById(id).orElseThrow(() -> {
                throw new IllegalStateException("This seller does not exist");
            });

            sellerRepository.deleteById(id);
            responseObject.setData("Delete success");
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }

    public ResponseObject login(LoginDTO loginDTO) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Seller seller = sellerRepository.findSellerByUsername(loginDTO.getUsername());
            log.info("Seller data {}", seller.getId());

            if(!seller.getPassword().equals(loginDTO.getPassword())){
                responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
                responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
                responseObject.setData("Your password is wrong");
                return responseObject;
            }
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
            responseObject.setData(new ResponseLoginDTO(seller));
        }catch (Exception e) {
            if(e.getMessage() == null){
                responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
                responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
                responseObject.setData("This username maybe wrong");
                return responseObject;
            }
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }
}
