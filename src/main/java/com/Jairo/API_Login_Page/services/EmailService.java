package com.Jairo.API_Login_Page.services;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${resend.api.key}")
    private String apiKey;

    public void sendConfirmationEmail(String to, String nome, String link) {
        Resend resend = new Resend(apiKey);

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("onboarding@resend.dev")
                .to(to)
                .subject("Confirme seu cadastro")
                .html(
                        "<p>Olá <strong>" + nome + "</strong>,</p>" +
                                "<p>Clique no link abaixo para confirmar seu cadastro:</p>" +
                                "<a href='" + link + "'>Confirmar cadastro</a>" +
                                "<p>O link expira em 24 horas.</p>" +
                                "<p>Se você não se cadastrou, ignore este email.</p>"
                )
                .build();

        try {
            resend.emails().send(params);
        } catch (ResendException e) {
            throw new RuntimeException("Erro ao enviar email: " + e.getMessage());
        }
    }
}