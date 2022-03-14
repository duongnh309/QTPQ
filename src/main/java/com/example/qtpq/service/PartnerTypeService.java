package com.example.qtpq.service;

import com.example.qtpq.dto.ResponseObject;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.model.PartnerType;
import com.example.qtpq.repository.PartnerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerTypeService {
    @Autowired
    private PartnerTypeRepository repository;

    public ResponseObject create(PartnerType partnerType){
        ResponseObject responseObject = new ResponseObject();
        try {
            PartnerType savedPartnerType = repository.save(partnerType);
            responseObject.setData(savedPartnerType);
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        }catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }

    public ResponseObject getList(){
        ResponseObject responseObject = new ResponseObject();
        try {
            List<PartnerType> partnerTypeList = repository.findAll();
            responseObject.setData(partnerTypeList);
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        }catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }

    public ResponseObject getListById(Long id){
        ResponseObject responseObject = new ResponseObject();
        if(!repository.existsById(id)){
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData("This partner type does not exist");
            return responseObject;
        }
        try {
            PartnerType partnerType = repository.findById(id).get();
            responseObject.setData(partnerType);
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        }catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }
    public ResponseObject update(PartnerType partnerType, Long id){
        ResponseObject responseObject = new ResponseObject();
        PartnerType dbPnType = repository.findById(id).get();
        if(partnerType.getPartnerTypeName().isEmpty()){
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData("PartnerType name cant empty");
            return responseObject;
        }
        if(!repository.existsById(id)){
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData("This partner type does not exist");
            return responseObject;
        }
        try {
            dbPnType.setPartnerTypeName(partnerType.getPartnerTypeName());
            PartnerType savedPartnerType = repository.save(dbPnType);
            responseObject.setData(savedPartnerType);
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        }catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }
    public ResponseObject delete(Long id) {
        ResponseObject responseObject = new ResponseObject();
        if(!repository.existsById(id)){
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData("This partner type does not exist");
            return responseObject;
        }
        try {
            repository.deleteById(id);
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        }catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }
}
