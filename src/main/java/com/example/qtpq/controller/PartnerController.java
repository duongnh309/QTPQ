package com.example.qtpq.controller;

import com.example.qtpq.dto.AddMenuToPartnerDTO;
import com.example.qtpq.dto.ResponseObject;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/partner")
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    @PostMapping("/createPartner")
    public ResponseEntity<ResponseObject> create(@RequestParam String partnerName,
                                                 @RequestParam Long partnerTypeId,
                                                 @RequestParam Long locationID[]) {
            ResponseObject responseObject = new ResponseObject();
            try{
                responseObject = partnerService.create(partnerName, partnerTypeId, locationID);
            } catch (Exception e){
                responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
                responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
                responseObject.setData(e.getMessage());
                return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }
    @GetMapping("/listPartner")
    public ResponseEntity<ResponseObject> getList(){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = partnerService.getAllPartner();
        } catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping("/getPartnerById/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable("id") Long id){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = partnerService.getPartnerByID(id);
        }catch (Exception e){
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @PutMapping("/updatePartner/{id}")
    public ResponseEntity<ResponseObject> updatePartner(@PathVariable("id") Long id, @RequestParam String name){
        ResponseObject responseObject = new ResponseObject();
        try{
            responseObject = partnerService.updatePartner(id, name);
        }catch (Exception e){
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @PostMapping("/addMember")
    public ResponseEntity<ResponseObject> addMember(@RequestBody AddMenuToPartnerDTO addDto){
        ResponseObject response = new ResponseObject();

        partnerService.addMenuToPartner(addDto);

        response.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        response.setStatus(ResponseCode.Common.SUCCESS.getCode());
        return ResponseEntity.ok().body(response);
    }
}
