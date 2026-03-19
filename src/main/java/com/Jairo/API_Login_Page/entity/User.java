package com.Jairo.API_Login_Page.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.EmbeddedColumnNaming;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Role;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_user")
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private  boolean enabled=false;




    public User(String nome,String email,String senha) {
        this.nome = nome;
        this.email=email;
        this.password=senha;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


}
