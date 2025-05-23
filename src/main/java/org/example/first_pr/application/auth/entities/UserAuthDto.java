package org.example.first_pr.application.auth.entities;

public record UserAuthDto(String username, String password, String userAgent, String ipAddress) {
}
