package com.Jairo.API_Login_Page.controller;


import com.Jairo.API_Login_Page.dto.AuthResponseDTO;
import com.Jairo.API_Login_Page.dto.LoginRequestDTO;
import com.Jairo.API_Login_Page.dto.UserCreateDTO;
import com.Jairo.API_Login_Page.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {


    @Autowired
    AuthService service;


    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserCreateDTO dto){
    service.register(dto);
    return ResponseEntity.status(201).body("cadastro realizado com sucesso ");
    }


    @GetMapping(value = "/confirm")
    public ResponseEntity<String> confirm(@RequestParam String token){
        service.confirm(token);
        return ResponseEntity.ok("email confirmado vôce ja pode fazer o login");
    }


    @PostMapping(value = "/login")
    public  ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto){
        return  ResponseEntity.ok(service.login(dto));


    }

}
