package com.example.qtpq.dto;

import com.example.qtpq.model.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
    private String menuName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public MenuDTO(Menu menu){
        this.menuName = menu.getName();
        this.description = menu.getDescription();
        this.startDate = menu.getStartDate();
        this.endDate = menu.getEndDate();
        this.status = menu.getStatus();
    }
}
