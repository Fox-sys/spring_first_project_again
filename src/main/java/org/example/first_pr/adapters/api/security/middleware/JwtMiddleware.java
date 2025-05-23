package org.example.first_pr.adapters.api.security.middleware;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.first_pr.application.auth.entities.Session;
import org.example.first_pr.application.auth.entities.User;
import org.example.first_pr.application.auth.exceptions.SessionUnprocessable;
import org.example.first_pr.application.auth.services.AuthService;
import org.example.first_pr.application.auth.services.JwtService;
import org.example.first_pr.application.auth.services.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JwtMiddleware extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService;
    private final AuthService authService;

    public JwtMiddleware(JwtService jwtService, @Lazy UserService userService, @Lazy AuthService authService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);

        UUID sessionId = jwtService.extractSessionId(token);
        Session session = authService.getSessionById(sessionId);

        if (session == null || !session.isActive()) {
            throw new SessionUnprocessable();
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userService.getUserByUsername(username);

            if (jwtService.isTokenValid(token, user)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);

    }
}
