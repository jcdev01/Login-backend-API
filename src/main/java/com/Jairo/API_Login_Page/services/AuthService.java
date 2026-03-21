package com.Jairo.API_Login_Page.services;

import com.Jairo.API_Login_Page.dto.AuthResponseDTO;
import com.Jairo.API_Login_Page.dto.LoginRequestDTO;
import com.Jairo.API_Login_Page.dto.UserCreateDTO;
import com.Jairo.API_Login_Page.entity.ConfirmationToken;
import com.Jairo.API_Login_Page.entity.User;
import com.Jairo.API_Login_Page.repository.ConfirmationTokenRepository;
import com.Jairo.API_Login_Page.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ConfirmationTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void register(UserCreateDTO dto) {
        if (userRepository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        User user = new User(
                dto.nome(),
                dto.email(),
                passwordEncoder.encode(dto.password())
        );
        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(24),
                user
        );
        tokenRepository.save(confirmationToken);

        String link = "https://login-backend-api-production-43f3.up.railway.app/auth/confirm?token=" + token;
        emailService.sendConfirmationEmail(user.getEmail(), user.getNome(), link);
    }

    @Transactional
    public void confirm(String token) {
        ConfirmationToken confirmationToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new RuntimeException("Email já confirmado");
        }

        if (confirmationToken.getExpireAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado");
        }

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        tokenRepository.save(confirmationToken);

        User user = confirmationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
    }

    public AuthResponseDTO login(LoginRequestDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );

        User user = userRepository.findByEmail(dto.email())
                .orElseThrow();

        String token = jwtService.generateToken(user);
        return new AuthResponseDTO(token);
    }
}