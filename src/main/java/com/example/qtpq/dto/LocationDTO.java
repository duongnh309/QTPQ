package com.example.qtpq.dto;

import com.example.qtpq.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Valid
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {
    @NotNull(message = "Name can't be null")
    private String name;
    @NotNull(message = "Address can't be null")
    private String address;

    public LocationDTO(Location location){
        this.name = location.getName();
        this.address = location.getAddress();
    }
}
