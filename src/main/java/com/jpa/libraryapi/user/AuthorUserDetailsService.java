package com.jpa.libraryapi.user;

import com.jpa.libraryapi.books.models.entities.Author;
import com.jpa.libraryapi.books.service.AuthorService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorUserDetailsService implements UserDetailsService {

    private final AuthorService service;

    public AuthorUserDetailsService(AuthorService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Author author = service.getByEmail(email);
        if(author == null) throw new UsernameNotFoundException("User not found");

        return AuthorUserDetails.of(author);
    }
}
