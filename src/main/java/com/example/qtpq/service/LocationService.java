package com.example.qtpq.service;

import com.example.qtpq.dto.LocationDTO;
import com.example.qtpq.dto.PartnerDTO;
import com.example.qtpq.dto.ResponseObject;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.model.Location;
import com.example.qtpq.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public ResponseObject getListLocation() {
        ResponseObject responseObject = new ResponseObject();
        try {
            List<Location> locations = locationRepository.findAll();
            List<LocationDTO>  locationDTOList= new ArrayList<>();

            for (Location location : locations) {
                LocationDTO locationDTO = new LocationDTO(location);
                locationDTOList.add(locationDTO);

            }

            responseObject.setData(locationDTOList);
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }

    public ResponseObject getLocationById(Long id) {
        ResponseObject responseObject = new ResponseObject();
        try {

            Location location = locationRepository.findById(id).get();
            log.info("Location {}", location.getName());
            LocationDTO locationDTO = new LocationDTO(location);

            responseObject.setData(locationDTO);
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }

    public ResponseObject createLocation(LocationDTO locationDTO) {
        ResponseObject responseObject = new ResponseObject();
        try {

            Location location = new Location(locationDTO);

            Location savedLocation = locationRepository.save(location);
            responseObject.setData(savedLocation);
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }

    public ResponseObject updateLocation(Long id, LocationDTO locationDTO) {
        ResponseObject responseObject = new ResponseObject();
        Location location = locationRepository.findById(id).orElseThrow(() -> {
            throw new IllegalStateException("This location does not exist");
        });
        try {
            location.setName(locationDTO.getName());
            location.setAddress(locationDTO.getAddress());

            Location savedLocation = locationRepository.save(location);
            responseObject.setData(new LocationDTO(savedLocation));
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }

    public ResponseObject deleteLocation(Long id) {
        ResponseObject responseObject = new ResponseObject();
        locationRepository.findById(id).orElseThrow(() -> {
            throw new IllegalStateException("This location does not exist");
        });
        try {

            locationRepository.deleteById(id);
            responseObject.setData("Delete success");
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }
        return responseObject;
    }
}
