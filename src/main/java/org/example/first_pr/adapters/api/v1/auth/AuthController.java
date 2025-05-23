package org.example.first_pr.adapters.api.v1.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.connector.Response;
import org.example.first_pr.adapters.api.v1.greeting.dtos.Message;
import org.example.first_pr.application.auth.entities.TokenDTO;
import org.example.first_pr.application.auth.entities.UserAuthDto;
import org.example.first_pr.application.auth.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public TokenDTO login(@RequestBody UserAuthDto request) {
        return authService.authenticate(request);
    }

    @PostMapping("/refresh")
    public TokenDTO refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        authService.makeSessionInActive(token);
        return ResponseEntity.ok().build();
    }
}