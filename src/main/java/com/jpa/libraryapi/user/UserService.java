package com.jpa.libraryapi.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;
   // private final PasswordEncoder encoder;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void save(User user){
        user.setSenha(encoder.encode(user.getSenha()));
        repository.save(user);
    }
    public User obterPorLogin(String login){
        return repository.findByLogin(login);
    }
    public User obterPorEmail(String email){
        return repository.findByEmail(email);
    }

}
