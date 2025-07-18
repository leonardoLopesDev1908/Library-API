package com.jpa.libraryapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.libraryapi.model.Client;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    
    Client findByClientId(String clientId);
}
