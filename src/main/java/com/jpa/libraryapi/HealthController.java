package com.jpa.libraryapi;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok().body("Server is healthy");
    }
}
