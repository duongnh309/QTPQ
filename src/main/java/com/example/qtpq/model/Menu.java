package com.example.qtpq.model;

import com.example.qtpq.message.request.MenuDataRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    private String status;
    //with seller
    @OneToOne(mappedBy = "menu", fetch = FetchType.LAZY, orphanRemoval = true)
    private Seller seller;
    //with order
    @OneToMany(mappedBy = "menu")
    private Set<Orders> orders;
    //with partnerInMenu
    @JsonIgnore
    @OneToMany(mappedBy = "menu")
    private List<PartnerInMenu> partnerInMenus = new ArrayList<>();
    //with products
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "menus")
    private Collection<Product> products = new HashSet<>();
    //with partnerType
    @ManyToOne
    private PartnerType partnerType;

    public Menu(MenuDataRequest dataReq){
        this.name=dataReq.getMenuName();
        this.description = dataReq.getDescription();
        this.startDate = dataReq.getStartDate();
        this.endDate = dataReq.getEndDate();
        this.status = dataReq.getStatus();
    }


}
