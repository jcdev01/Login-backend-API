package com.Jairo.API_Login_Page.services;


import com.Jairo.API_Login_Page.entity.User;
import com.Jairo.API_Login_Page.exceptions.ResoruceNotFoundExption;
import com.Jairo.API_Login_Page.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public List<User> FindAll(){
        return userRepository.findAll();
    }


    public User FindByID(Long id){
        User user=userRepository.findById(id).orElseThrow(()-> new ResoruceNotFoundExption("Id do usuário não encontrado"));

        return user;

    }



    public void UptadePassword(Long id , String senha){
          User entity = userRepository.findById(id).orElseThrow( ()-> new ResoruceNotFoundExption("Id do usuário não encontrado"));
          entity.setPassword(passwordEncoder.encode(senha));

        userRepository.save(entity);

    }


}
