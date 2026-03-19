package com.Jairo.API_Login_Page.repository;

import com.Jairo.API_Login_Page.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);



}
