package com.Jairo.API_Login_Page.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserPasswordUpdateDTO(

        @NotBlank(message = "senha é obrigatoria")
        @Size(min = 8,message = "a senha deve conter pelo menos 8 caracteres")
        String password) {
}
