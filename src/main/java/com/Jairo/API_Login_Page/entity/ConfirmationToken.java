package com.Jairo.API_Login_Page.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
public class ConfirmationToken {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column (nullable = false)
    private LocalDateTime expireAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private  User user;

    public ConfirmationToken(String token,LocalDateTime createdAt,LocalDateTime expireAt, User user) {
        this.token = token;
        this.createdAt=createdAt;
        this.expireAt=expireAt;
        this.user=user;
    }


}
