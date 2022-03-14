package com.example.qtpq.model;

import com.example.qtpq.dto.LocationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "location")
    private List<Orders> orders = new ArrayList<>();

    @ManyToMany
    private List<Partner> partners;

    public Location(LocationDTO locationDTO){
        this.name = locationDTO.getName();
        this.address = locationDTO.getAddress();
    }
}
