package com.jpa.libraryapi.client.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.libraryapi.client.models.Client;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    
    Client findByClientId(String clientId);
}
