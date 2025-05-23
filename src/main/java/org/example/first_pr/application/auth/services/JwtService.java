package org.example.first_pr.application.auth.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.first_pr.application.auth.AuthProperties;
import org.example.first_pr.application.auth.entities.Session;
import org.example.first_pr.application.auth.entities.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class JwtService {
    private final AuthProperties authProperties;

    public JwtService(AuthProperties authProperties) {
        this.authProperties = authProperties;
    }

    public String generateToken(User user, Session session, long expireTime) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId().toString());
        claims.put("username", user.getUsername());
        claims.put("session_id", session.id().toString());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(Keys.hmacShaKeyFor(authProperties.getSecretKey().getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(authProperties.getSecretKey().getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public UUID extractSessionId(String token) {
        String sessionIdStr = extractAllClaims(token).get("session_id", String.class);
        return UUID.fromString(sessionIdStr);
    }

    public boolean isTokenValid(String token, User user) {
        String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public String generateAccessToken(User user, Session session) {
        return generateToken(user, session, authProperties.getAccessExpireTimeMs());
    }

    public String generateRefreshToken(User user, Session session) {
        return generateToken(user, session, authProperties.getRefreshExpireTimeMs());
    }
}
