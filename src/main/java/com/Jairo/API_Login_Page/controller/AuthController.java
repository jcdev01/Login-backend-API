package com.Jairo.API_Login_Page.controller;


import com.Jairo.API_Login_Page.dto.*;
import com.Jairo.API_Login_Page.services.AuthService;
import com.Jairo.API_Login_Page.services.GoogleAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {


    @Autowired
    AuthService authService;

    @Autowired
    GoogleAuthService googleAuthService;






    @PostMapping(value = "/register")
    public ResponseEntity<MessageReponseDTO> register(@RequestBody @Valid UserCreateDTO dto){
    authService.register(dto);
    return ResponseEntity.status(201).body(new MessageReponseDTO("email registrado com sucesso aguardando autenticação"));
    }


    @GetMapping(value = "/confirm")
    public ResponseEntity<MessageReponseDTO> confirm(@RequestParam String token){
        authService.confirm(token);
        return ResponseEntity.ok(new MessageReponseDTO("Autenticação bem sucedida"));
    }


    @PostMapping(value = "/login")
    public  ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto){
        return  ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping(value = "/google")
    public ResponseEntity<AuthResponseDTO> google(@RequestBody GoogleAuthRequestDTO dto){
        String jwt= googleAuthService.authenticate(dto.token());

      return   ResponseEntity.ok(new AuthResponseDTO(jwt));


    }

}
