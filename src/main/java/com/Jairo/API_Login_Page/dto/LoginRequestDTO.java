package com.Jairo.API_Login_Page.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(

        @NotBlank(message = "email é obrigatório")
        @Email(message = "email invalido")
        String email,

        @NotBlank(message = "senha é obrigatoria")
        String password


            )

{}
