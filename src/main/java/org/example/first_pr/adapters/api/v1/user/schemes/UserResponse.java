package org.example.first_pr.adapters.api.v1.user.schemes;

import org.example.first_pr.application.auth.entities.User;

import java.util.UUID;

public record UserResponse(UUID id, String username, String firstName, String lastName) {
    public UserResponse(User user) {
        this(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName());
    }
}
