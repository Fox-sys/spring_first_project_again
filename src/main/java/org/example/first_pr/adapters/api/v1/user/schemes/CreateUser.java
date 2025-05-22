package org.example.first_pr.adapters.api.v1.user.schemes;

import org.example.first_pr.application.auth.entities.User;

public record CreateUser(String firstName, String lastName, String username) {
    public User toEntity() {
        return new User(null, firstName, lastName, username);
    }
}
