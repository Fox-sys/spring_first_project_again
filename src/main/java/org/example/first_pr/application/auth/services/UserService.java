package org.example.first_pr.application.auth.services;

import org.example.first_pr.application.auth.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User get_current_user() {
        return new User(1, "Хто", "Я");
    }
}
