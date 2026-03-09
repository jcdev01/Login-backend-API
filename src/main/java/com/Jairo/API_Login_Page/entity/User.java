package com.Jairo.API_Login_Page.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.EmbeddedColumnNaming;


@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;


    public User(String nome,String email,String senha) {
        this.nome = nome;
        this.email=email;
        this.senha=senha;
    }




}
