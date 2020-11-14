package com.gamestore.gamestore.domain.repository;

import com.gamestore.gamestore.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);

}
