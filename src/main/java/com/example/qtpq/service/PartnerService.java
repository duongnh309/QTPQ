package com.example.qtpq.service;

import com.example.qtpq.dto.AddMenuToPartnerDTO;
import com.example.qtpq.dto.PartnerDTO;
import com.example.qtpq.dto.ResponseObject;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.model.*;
import com.example.qtpq.repository.*;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PartnerService {
    private PartnerRepository partnerRepository;
    private PartnerTypeRepository partnerTypeRepository;
    private LocationRepository locationRepository;
    private MenuRepository menuRepository;
    private PartnerInMenuRepository partnerInMenuRepository;

    public ResponseObject create(String partnerName, Long partnerTypeID, Long locationID[]) {
        ResponseObject responseObject = new ResponseObject();
        PartnerType partnerType = partnerTypeRepository.findById(partnerTypeID).orElseThrow(() -> {
            throw new IllegalStateException("This partner type does not exist");
        });
        List<Location> locationList = new ArrayList<>();
        for (int i = 0; i < locationID.length; i++) {
            Location location = locationRepository.findById(locationID[i]).orElseThrow(() -> {
                throw new IllegalStateException("This location type does not exist");
            });
            locationList.add(location);
        }
        try {
            Partner partner = new Partner(partnerName, partnerType);
            partner.setLocations(locationList);
            Partner savedPartner = partnerRepository.save(partner);


            responseObject.setData(new PartnerDTO(savedPartner));
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }

        return responseObject;
    }

    public ResponseObject getAllPartner() {
        ResponseObject responseObject = new ResponseObject();
        try {
            List<PartnerDTO> partnersDTO = new ArrayList<>();
            List<Partner> partnerList = partnerRepository.findAll();
            for (Partner partner : partnerList) {
                PartnerDTO partners = new PartnerDTO(partner);
                partnersDTO.add(partners);

            }

            responseObject.setData(partnersDTO);
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }

    public ResponseObject getPartnerByID(Long id) {
        ResponseObject responseObject = new ResponseObject();
        if (!partnerRepository.existsById(id)) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData("This partner does not exist");
            return responseObject;
        }
        try {
            Partner partner = partnerRepository.findById(id).get();
            PartnerDTO partnerDTO = new PartnerDTO(partner);

            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
            responseObject.setData(partnerDTO);
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }

    public ResponseObject updatePartner(Long id, String name) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Partner partner = partnerRepository.findById(id).orElseThrow(() -> {
                throw new IllegalStateException("This partner does not exits");
            });
            partner.setName(name);

            Partner savedPartner = partnerRepository.save(partner);
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
            responseObject.setData(new PartnerDTO(savedPartner));
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }

    public void addMenuToPartner(AddMenuToPartnerDTO addDTO) {

        addDTO.setStartDate(LocalDate.now());
        Menu menu = menuRepository.findById(addDTO.getMenuId()).orElseThrow(() -> {
            throw new IllegalStateException("This menu does not exist");
        });


        Partner partner = partnerRepository.findById(addDTO.getPartnerId()).orElseThrow(() -> {
            throw new IllegalStateException("This partner does not exist");
        });

        if (partnerInMenuRepository.isInPartner(addDTO.getMenuId(), addDTO.getPartnerId()) == 1) {
            throw new IllegalStateException("This menu already in this partner");
        }
        if (addDTO.getEndDate().isBefore(addDTO.getStartDate())) {
            throw new IllegalStateException("End date must be after start date");
        }

        PartnerInMenu pim = new PartnerInMenu();
        pim.setStartDate(addDTO.getStartDate());
        pim.setEndDate(addDTO.getEndDate());
        pim.setPartner(partner);
        pim.setMenu(menu);
        partnerInMenuRepository.save(pim);
    }
}
