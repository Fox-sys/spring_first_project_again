package org.example.first_pr.application.auth.services;

import org.example.first_pr.adapters.db.repositories.UserRepo;
import org.example.first_pr.application.auth.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User get_current_user() {
        List<User> users = userRepo.getUsers();
        return users.stream().findFirst().orElse(null);
    }
}
