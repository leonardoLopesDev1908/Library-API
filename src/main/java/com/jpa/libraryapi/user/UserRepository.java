package com.jpa.libraryapi.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID>{
    
    User findByLogin(String login);

    User findByEmail(String email);
}
