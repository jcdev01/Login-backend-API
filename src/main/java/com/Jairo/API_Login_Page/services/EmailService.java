package com.Jairo.API_Login_Page.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sendinblue.ApiClient;
import sendinblue.ApiException;
import sendinblue.Configuration;
import sibApi.TransactionalEmailsApi;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailSender;
import sibModel.SendSmtpEmailTo;

import java.util.List;

@Service
public class EmailService {

    @Value("${brevo.api.key}")
    private String apiKey;

    public void sendConfirmationEmail(String to, String nome, String link) {
        try {
            ApiClient client = Configuration.getDefaultApiClient();
            client.setApiKey(apiKey);

            TransactionalEmailsApi api = new TransactionalEmailsApi();

            SendSmtpEmail email = new SendSmtpEmail();
            email.setTo(List.of(new SendSmtpEmailTo().email(to).name(nome)));
            email.setSender(new SendSmtpEmailSender().email("jairocostanascimento@gmail.com").name("API Login"));
            email.setSubject("Confirme seu cadastro");
            email.setTextContent(
                    "Olá " + nome + ",\n\n" +
                            "Clique no link abaixo para confirmar seu cadastro:\n\n" +
                            link + "\n\n" +
                            "O link expira em 24 horas."
            );

            api.sendTransacEmail(email);
        } catch (ApiException e) {
            throw new RuntimeException("Erro ao enviar email: " + e.getMessage());
        }
    }
}