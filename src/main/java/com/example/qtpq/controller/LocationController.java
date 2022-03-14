package com.example.qtpq.controller;

import com.example.qtpq.dto.LocationDTO;
import com.example.qtpq.dto.ResponseObject;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/location")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @GetMapping("/getListLocation")
    public ResponseEntity<ResponseObject> getList(){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = locationService.getListLocation();

        } catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping("/getLocationById/{id}")
    public ResponseEntity<ResponseObject> getLocationById(@PathVariable("id") Long id){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = locationService.getLocationById(id);
        }catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @PostMapping("/createLocation")
    public ResponseEntity<ResponseObject> createLocation(@RequestBody LocationDTO location){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = locationService.createLocation(location);
        }catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @PutMapping("/updateLocation/{id}")
    public ResponseEntity<ResponseObject> updateLocation(@PathVariable("id") Long id, @RequestBody LocationDTO location){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = locationService.updateLocation(id, location);
        }
        catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @DeleteMapping("/deleteLocation/{id}")
    public ResponseEntity<ResponseObject> deleteLocation(@PathVariable("id") Long id){
        ResponseObject responseObject = new ResponseObject();
        try {
            responseObject = locationService.deleteLocation(id);
        }
        catch (Exception e){
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(ResponseCode.Common.FAILED.getCode());
            responseObject.setData(e.getMessage());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
