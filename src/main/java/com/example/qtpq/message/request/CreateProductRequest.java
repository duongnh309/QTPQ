package com.example.qtpq.message.request;

import com.example.qtpq.model.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Valid
public class CreateProductRequest implements Serializable {

    @NotNull
    private String productName;
    @NotNull
    private double unitPrice;
    @NotNull
    private int quanlity;
    @NotNull
    private String img;
    @NotNull
    private String description;
    @NotNull
    private Category categories;

}
