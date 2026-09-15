package com.jpa.libraryapi.user;

import com.jpa.libraryapi.books.service.AuthorService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthorUserDetailsService implements UserDetailsService {

    private final AuthorService service;

    public AuthorUserDetailsService(AuthorService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return AuthorUserDetails.of(service.getByEmail(email));
    }
}
