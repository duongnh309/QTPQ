package com.example.qtpq.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class PartnerType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "partner_type_name")
    private String partnerTypeName;
    @JsonIgnore
    @OneToMany(mappedBy = "partnerType")
    private List<Menu> menus = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "partnerType")
    private List<Partner> partners = new ArrayList<>();

    public PartnerType(Long id, String name){
        this.id = id;
        this.partnerTypeName = name;
    }
}
