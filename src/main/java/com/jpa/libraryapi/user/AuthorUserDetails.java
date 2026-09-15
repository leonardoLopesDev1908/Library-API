package com.jpa.libraryapi.user;

import com.jpa.libraryapi.books.models.entities.Author;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Builder
public class AuthorUserDetails implements UserDetails {

    private UUID id;
    private String email;
    private String name;
    private String phone;

    private static Collection<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("AUTHOR"));

    public static AuthorUserDetails of(Author author) {
        return AuthorUserDetails.builder()
                .id(author.getId())
                .name(author.getName())
                .email(author.getEmail())
                .phone(author.getPhone())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
