package org.example.first_pr.application.auth.services;

import org.example.first_pr.application.auth.entities.TokenDTO;
import org.example.first_pr.application.auth.entities.User;
import org.example.first_pr.application.auth.entities.UserAuthDto;
import org.example.first_pr.application.auth.exceptions.UserNotFoundByUsernameException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthService(AuthenticationManager authManager, JwtService jwtService, UserService userService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    public TokenDTO authenticate(UserAuthDto dto) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));

        User user = (User) authentication.getPrincipal();

        return new TokenDTO(
                jwtService.generateAccessToken(user),
                jwtService.generateRefreshToken(user)
        );
    }

    public TokenDTO refresh(String refreshToken) {
        String username = jwtService.extractUsername(refreshToken);
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundByUsernameException(username);
        }

        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw new RuntimeException("Invalid refresh token");
        }

        return new TokenDTO(
                jwtService.generateAccessToken(user),
                jwtService.generateRefreshToken(user)
        );
    }
}