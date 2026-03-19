package com.Jairo.API_Login_Page.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateDTO(

        @NotBlank(message = "nome é obrigatorio")
        String nome,

        @NotBlank(message = "email é obrigatorio")
        @Email(message = "formato de email inválido")
        String email,

        @NotBlank(message = "senha é obrigatorio ")
        @Size(min = 8,message = "a senha deve ter pelo menos 8 caracteres")
        String password) {
}
