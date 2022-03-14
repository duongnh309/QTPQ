package com.example.qtpq.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {
    @NotBlank(message = "Please type username")
    private String username;
    @NotBlank(message = "Please type password")
    private String password;
}
