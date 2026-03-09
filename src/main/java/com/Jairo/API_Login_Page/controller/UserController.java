package com.Jairo.API_Login_Page.controller;


import com.Jairo.API_Login_Page.dto.UserResponseDTO;
import com.Jairo.API_Login_Page.entity.User;
import com.Jairo.API_Login_Page.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> FindAll(){
        List<User> list=userService.FindAll();

        List<UserResponseDTO> dtoList=list.stream()
                .map(user -> new UserResponseDTO(user.getId(),
                user.getNome(),
                user.getEmail()
                ))
                .toList();

    return ResponseEntity.ok(dtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> FinByID(@PathVariable Long id){
        User user=userService.FindByID(id);
        UserResponseDTO userDTO=new UserResponseDTO(
                user.getId(),
                user.getNome(),
                user.getEmail());

        return ResponseEntity.ok(userDTO);



    }


}
