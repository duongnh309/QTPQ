package com.example.qtpq.dto;

import com.example.qtpq.model.Menu;
import com.example.qtpq.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
    private Long id;
    private String menuName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private List<Product> products = new ArrayList<>();

    public MenuDTO(Menu menu){
        this.id = menu.getId();
        this.menuName = menu.getName();
        this.description = menu.getDescription();
        this.startDate = menu.getStartDate();
        this.endDate = menu.getEndDate();
        this.status = menu.getStatus();
        this.products = convertProduct(menu);
    }

    public ArrayList<Product> convertProduct(Menu menu){
        Product[] products = menu.getProducts().toArray(new Product[menu.getProducts().size()]);
        ArrayList<Product> productArrayList = new ArrayList<>();
        for (int i = 0; i < menu.getProducts().size(); i++)  {
            productArrayList.add(products[i]);
        }
        return productArrayList;
    }
}
