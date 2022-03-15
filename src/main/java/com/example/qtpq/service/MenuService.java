package com.example.qtpq.service;

import com.example.qtpq.dto.MenuDTO;
import com.example.qtpq.dto.ResponseObject;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.message.request.MenuDataRequest;
import com.example.qtpq.model.Menu;
import com.example.qtpq.model.PartnerType;
import com.example.qtpq.model.Product;
import com.example.qtpq.repository.MenuRepository;
import com.example.qtpq.repository.PartnerTypeRepository;
import com.example.qtpq.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.*;

@Service
@Slf4j
@AllArgsConstructor
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private ProductRepository productRepository;

    private PartnerTypeRepository partnerTypeRepository;

    public ResponseObject getListMenu() {
        ResponseObject responseObject = new ResponseObject();
        try {
            List<Menu> listMenu = menuRepository.findAll();
            List<MenuDTO> listDTO = new ArrayList<>();
            for (Menu menu : listMenu) {
                MenuDTO menus = new MenuDTO(menu);
                listDTO.add(menus);
            }
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setData(listDTO);
        } catch (Exception e) {
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            log.error("findAll Menu error {} ", e.getMessage());
        }
        return responseObject;
    }

    public ResponseObject getMenuById(Long id) {
        ResponseObject responseObject = new ResponseObject();
        Menu menuOptional = menuRepository.findById(id).get();
        MenuDTO menuDTO = new MenuDTO(menuOptional);
        if (menuDTO == null) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData("This menu does not exist");
            return responseObject;
        }
        responseObject.setData(menuDTO);
        responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
        return responseObject;
    }

    public ResponseObject createMenu(MenuDataRequest menuRequest, Long productId[]) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Menu menu = new Menu(menuRequest);
            for (int i = 0; i < productId.length; i++) {
                Optional<Product> productOptional = productRepository.findById(productId[i]);
                log.info("Product data {}", productOptional.isPresent());
                Product product = productOptional.get();

                menu.getProducts().add(product);
                product.getMenus().add(menu);
            }

            PartnerType partnerTypeInDB = partnerTypeRepository.findById(menuRequest.getPartnerTypeId()).orElseThrow(() ->{
                throw new IllegalStateException("This partner type does not exist");
            });

            PartnerType partnerType = new PartnerType(partnerTypeInDB.getId(), partnerTypeInDB.getPartnerTypeName());
            menu.setPartnerType(partnerType);
            Menu savedMenu = menuRepository.save(menu);
            responseObject.setData(savedMenu);
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }

    public ResponseObject deleteMenu(Long id) {
        ResponseObject responseObject = new ResponseObject();


        try {
            Menu menu = menuRepository.findById(id).get();
            menu.setStatus("CLOSED");
            Menu newMenu = menuRepository.save(menu);
            responseObject.setData(newMenu);
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }
@Transient
    public ResponseObject updateMenu(Long id , MenuDataRequest request, Long productId[]) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Set<Product> productSet = new HashSet<>();
            Menu menuOptional = menuRepository.findById(id).get();
            menuOptional.setDescription(request.getDescription());
            menuOptional.setEndDate(request.getEndDate());
            menuOptional.setName(request.getMenuName());
            menuOptional.setStartDate(request.getStartDate());
            menuOptional.setStatus(request.getStatus());
            menuOptional.setPartnerType(partnerTypeRepository.findById(request.getPartnerTypeId()).get());
            for (int i = 0; i < productId.length; i++) {
                Product product = productRepository.findById(productId[i]).get();
               menuRepository.addProductToMenu(product.getId(), menuOptional.getId());
            }

            Menu savedMenu = menuRepository.save(menuOptional);
            responseObject.setData(savedMenu);
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }
}
