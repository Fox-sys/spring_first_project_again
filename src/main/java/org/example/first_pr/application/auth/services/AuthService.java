package org.example.first_pr.application.auth.services;

import org.example.first_pr.application.auth.entities.Session;
import org.example.first_pr.application.auth.entities.TokenDTO;
import org.example.first_pr.application.auth.entities.User;
import org.example.first_pr.application.auth.entities.UserAuthDto;
import org.example.first_pr.application.auth.exceptions.SessionUnprocessable;
import org.example.first_pr.application.auth.exceptions.UserNotFoundByUsernameException;
import org.example.first_pr.application.auth.interfaces.ISessionRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final ISessionRepo sessionRepo;

    public AuthService(AuthenticationManager authManager, JwtService jwtService, UserService userService, ISessionRepo sessionRepo) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userService = userService;
        this.sessionRepo = sessionRepo;
    }

    public Session getSessionById(UUID id) {
        return sessionRepo.getById(id);
    }

    public TokenDTO authenticate(UserAuthDto dto) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));

        User user = (User) authentication.getPrincipal();
        Session session = sessionRepo.save(user, dto);
        return new TokenDTO(
                jwtService.generateAccessToken(user, session),
                jwtService.generateRefreshToken(user, session)
        );
    }

    public TokenDTO refresh(String refreshToken) {
        String username = jwtService.extractUsername(refreshToken);
        UUID sessionId = jwtService.extractSessionId(refreshToken);
        User user = userService.getUserByUsername(username);
        Session session = getSessionById(sessionId);
        if (session == null || !session.isActive()) {
            throw new SessionUnprocessable();
        }
        if (user == null) {
            throw new UserNotFoundByUsernameException(username);
        }

        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw new SessionUnprocessable("Invalid refresh token");
        }

        return new TokenDTO(
                jwtService.generateAccessToken(user, session),
                jwtService.generateRefreshToken(user, session)
        );
    }

    public void makeSessionInActive(String token) {
        UUID sessionId = jwtService.extractSessionId(token);
        sessionRepo.deactivate(sessionId);
    }
}