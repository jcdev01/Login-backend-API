package com.Jairo.API_Login_Page.services;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.Jairo.API_Login_Page.entity.User;
import com.Jairo.API_Login_Page.repository.UserRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.UUID;

@Service
public class GoogleAuthService {


    @Autowired
    UserRepository repository;

    @Autowired
    JwtService jwtService;

    @Autowired

    PasswordEncoder passwordEncoder;

    @Value("${google.client.id}")
    private String cliente_id;

    public String authenticate(String googleToken) {

        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(),
                    JacksonFactory.getDefaultInstance()
            )
                    .setAudience(Collections.singletonList(cliente_id))
                    .build();
            GoogleIdToken idToken = verifier.verify(googleToken);

            if (idToken == null) {
                throw new RuntimeException("Token inválido");
            }

            GoogleIdToken.Payload payload = idToken.getPayload();

            String email = payload.getEmail();
            String name = (String) payload.get("name");

            //  busca usuário
            User user = repository.findByEmail(email)
                    .orElseGet(() -> {
                        User newUser = new User();
                        newUser.setEmail(email);
                        newUser.setNome(name);
                        newUser.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
                        newUser.setEnabled(true);
                        return repository.save(newUser);
                    });

            //  gera o JWT
            return jwtService.generateToken(user);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao autenticar com Google", e);
        }
    }
}




