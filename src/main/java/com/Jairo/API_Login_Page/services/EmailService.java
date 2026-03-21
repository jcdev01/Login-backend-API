package com.Jairo.API_Login_Page.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendConfirmationEmail(String to, String nome, String link) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("jairocostanascimento@gmail.com");
        message.setTo(to);
        message.setSubject("Confirme seu cadastro");
        message.setText(
                "Olá " + nome + ",\n\n" +
                        "Clique no link abaixo para confirmar seu cadastro:\n\n" +
                        link + "\n\n" +
                        "O link expira em 24 horas.\n\n" +
                        "Se você não se cadastrou, ignore este email."
        );

        mailSender.send(message);
    }
}