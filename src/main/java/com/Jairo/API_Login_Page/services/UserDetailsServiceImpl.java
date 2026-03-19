package com.Jairo.API_Login_Page.services;

import com.Jairo.API_Login_Page.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).orElseThrow(() ->new UsernameNotFoundException("usuario não encontrado"+email));
    }
}
