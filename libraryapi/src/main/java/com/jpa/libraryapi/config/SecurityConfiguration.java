package com.jpa.libraryapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

import com.jpa.libraryapi.security.JwtAuthenticationToken;
import com.jpa.libraryapi.security.LoginSocialSuccesHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled=true, jsr250Enabled=true)
public class SecurityConfiguration {
    
    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(
            HttpSecurity http, 
            LoginSocialSuccesHandler loginSocial,
            JwtAuthenticationToken jwtAuthenticationToken) throws Exception {
                
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(form -> form.loginPage("/login"))
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/login/**").permitAll();
                    authorize.requestMatchers( "/livros").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .oauth2Login(oauth2 -> oauth2
                    .loginPage("/login")
                    .successHandler(loginSocial))
                .oauth2ResourceServer(oauth2RS -> oauth2RS.jwt(Customizer.withDefaults()))
                .addFilterAfter(jwtAuthenticationToken, BearerTokenAuthenticationFilter.class)
                .build();
    }

    //@Bean 
    // public UserDetailsService userDetailsService(UsuarioService usuarioService){
    // /*/    
    //     UserDetails user1 = User.builder()
    //             .username("usuario")
    //             .password(encoder.encode("123"))
    //             .roles("USER")
    //             .build();
                
    //     UserDetails user2 = User.builder()
    //             .username("admin")
    //             .password(encoder.encode("231"))
    //             .roles("ADMIN")
    //             .build();

    //     return new InMemoryUserDetailsManager(user1, user2);
    //     */
    //     //return new CustomUserDetailsService(usuarioService);
    // }


    //Configura prefixo role
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults(){
        return new GrantedAuthorityDefaults("");
    }

    //Configura prefixo scope
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        var authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthorityPrefix("");

        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
        
        return converter;
    }
}
