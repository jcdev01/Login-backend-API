package com.Jairo.API_Login_Page.config;

import com.Jairo.API_Login_Page.entity.User;
import com.Jairo.API_Login_Page.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

    @Autowired
    UserRepository userRepository;






    @Override
    public void run(String... args) throws Exception {

        User user1=new User("Jairo","Jairocostanascimento@gmail.com","1234");
        userRepository.save(user1);


    }
}
