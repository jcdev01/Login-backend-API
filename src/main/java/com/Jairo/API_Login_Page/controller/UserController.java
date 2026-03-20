package com.Jairo.API_Login_Page.controller;



import com.Jairo.API_Login_Page.dto.UserResponseDTO;
import com.Jairo.API_Login_Page.dto.UserPasswordUpdateDTO;
import com.Jairo.API_Login_Page.entity.User;
import com.Jairo.API_Login_Page.services.AuthService;
import com.Jairo.API_Login_Page.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PutMapping(value = "/{id}/senha")
    public ResponseEntity<Void> UpdatePassword(@PathVariable Long id, @RequestBody @Valid UserPasswordUpdateDTO userDTO){

        userService.UptadePassword(id,userDTO.password());


        return ResponseEntity.noContent().build();


    }


}
