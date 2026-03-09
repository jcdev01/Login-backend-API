package com.Jairo.API_Login_Page.services;


import com.Jairo.API_Login_Page.entity.User;
import com.Jairo.API_Login_Page.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<User> FindAll(){
        return userRepository.findAll();
    }


    public User FindByID(Long id){
        Optional<User> obj=userRepository.findById(id);
        return obj.get();

    }

}
