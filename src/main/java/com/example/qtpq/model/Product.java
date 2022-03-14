package com.example.qtpq.model;

import com.example.qtpq.message.request.CreateProductRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "unit_price")
    private double unitPrice;
    private int quanlity;
    @Column(name = "image_link")
    private String imgLink;
    private String description;

    //Rela with Category
    @ManyToOne
    private Category categories;
    //Rela with menu
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "product_menu",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id"))
    private Collection<Menu> menus = new HashSet<>();
    //rela with orderDetail
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> productDetails = new ArrayList<>();


    public Product(CreateProductRequest createProductRequest) {
        this.productName = createProductRequest.getProductName();
        this.unitPrice = createProductRequest.getUnitPrice();
        this.quanlity = createProductRequest.getQuanlity();
        this.imgLink = createProductRequest.getImg();
        this.description = createProductRequest.getDescription();
        this.categories = createProductRequest.getCategories();
    }
}
