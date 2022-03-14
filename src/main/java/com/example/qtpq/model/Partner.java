package com.example.qtpq.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "partner_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "partnerType_id", nullable = false)
    private PartnerType partnerType;
    //with PIM
    @JsonIgnore
    @OneToMany(mappedBy = "partner")
    private List<PartnerInMenu> partnerInMenu = new ArrayList<>();
    //with location
    @ManyToMany(mappedBy = "partners")
    private List<Location> locations;
    //with seller
    @OneToMany(mappedBy = "partner")
    private Set<Seller> sellers;

    public Partner(String name, PartnerType partnerType) {
        this.name = name;
        this.partnerType = partnerType;
    }


}
