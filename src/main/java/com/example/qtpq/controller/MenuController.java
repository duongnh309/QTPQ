package com.example.qtpq.controller;

import com.example.qtpq.dto.ResponseObject;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.message.request.MenuDataRequest;
import com.example.qtpq.model.Menu;
import com.example.qtpq.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("/api")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/menu/getMenus")
    public ResponseEntity<ResponseObject> getListMenu() {
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = menuService.getListMenu();
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
            log.error(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/menu/getMenuById/{id}")
    public ResponseEntity<ResponseObject> getMenuByID(@PathVariable("id") Long id) {
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = menuService.getMenuById(id);
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
            log.error(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/menu/getProductInMenuWithName/{id}")
    public ResponseEntity<ResponseObject> getProductInMenuWithName(@PathVariable("id") Long menuId,
                                                                   @RequestParam String productName) {
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = menuService.getProductByName(menuId, productName);
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
            log.error(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/menu/create")
    public ResponseEntity<ResponseObject> createMenu(@RequestBody MenuDataRequest menu, @RequestParam Long productId[]) {
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = menuService.createMenu(menu, productId);
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
//    @PutMapping("/menu/update/{id}")
//    public ResponseEntity<ResponseObject> updateMenu(@PathVariable("id") Long id, @RequestBody MenuDataRequest menu, @RequestParam Long productsId[]) {
//        ResponseObject responseObject = new ResponseObject();
//        try {
//            responseObject = menuService.updateMenu(id,menu,productsId);
//        }catch (Exception e){
//            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
//            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
//            responseObject.setData(e.getMessage());
//            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(responseObject, HttpStatus.OK);
//    }

    @DeleteMapping("/menu/delete/{id}")
    public ResponseEntity<ResponseObject> deleteMenu(@PathVariable("id") Long id) {
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = menuService.deleteMenu(id);
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
            log.error(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
