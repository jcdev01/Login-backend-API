package com.Jairo.API_Login_Page.repository;

import com.Jairo.API_Login_Page.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);


}
