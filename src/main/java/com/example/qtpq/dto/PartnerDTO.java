package com.example.qtpq.dto;

import com.example.qtpq.model.Partner;
import com.example.qtpq.model.PartnerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerDTO {
    private String name;
    private PartnerType type;
    private LocationDTO location;

    public PartnerDTO(Partner partner) {
        this.name = partner.getName();
        this.type = partner.getPartnerType();
        for (int i = 0; i < partner.getLocations().size(); i++) {
            this.location = new LocationDTO(partner.getLocations().get(i));
        }
    }
}
