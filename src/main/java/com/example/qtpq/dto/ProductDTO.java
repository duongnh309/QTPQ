package com.example.qtpq.dto;

import com.example.qtpq.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {
    private String description;
    private String img;
    private String productName;
    private int quanlity;
    private double unitPrice;
    private String categorynName;


    public ProductDTO(Product product) {
        this.description = product.getDescription();
        this.img = product.getImgLink();
        this.productName = product.getProductName();
        this.quanlity = product.getQuanlity();
        this.unitPrice = product.getUnitPrice();
        this.categorynName = product.getCategories().getCategoryName();
    }
}
