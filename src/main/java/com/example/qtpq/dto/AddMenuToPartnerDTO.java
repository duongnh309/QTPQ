package com.example.qtpq.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Valid
public class AddMenuToPartnerDTO {
    @NotNull(message = "Menu id cant be blank")
    private Long menuId;
    @NotNull(message = "Partner id cant be blank")
    private Long partnerId;
    @JsonIgnore
    private LocalDate startDate;
    @NotNull(message = "Please enter end date")
    private LocalDate endDate;

}
