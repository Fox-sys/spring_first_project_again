package org.example.first_pr.adapters.api.v1.user.dtos;

import org.example.first_pr.application.auth.entities.User;

public record UserDTO(int id, String fist_name, String last_name) {
    public UserDTO(User user) {
        this(user.getId(), user.getFirstName(), user.getLastName());
    }
}
