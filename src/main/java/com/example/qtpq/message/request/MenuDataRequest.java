package com.example.qtpq.message.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Valid
@ToString
public class MenuDataRequest {
    @NotNull
    private String menuName;
    @NotNull
    private String description;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private String status;
    @NotNull
    private Long partnerTypeId;

}
