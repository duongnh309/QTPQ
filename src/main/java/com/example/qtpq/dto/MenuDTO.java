package com.example.qtpq.dto;

import com.example.qtpq.model.Menu;
import com.example.qtpq.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
    private String menuName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Collection<Product> products;

    public MenuDTO(Menu menu){
        this.menuName = menu.getName();
        this.description = menu.getDescription();
        this.startDate = menu.getStartDate();
        this.endDate = menu.getEndDate();
        this.status = menu.getStatus();
        this.products = menu.getProducts();
    }
}
