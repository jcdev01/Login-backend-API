package com.Jairo.API_Login_Page.repository;

import com.Jairo.API_Login_Page.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
