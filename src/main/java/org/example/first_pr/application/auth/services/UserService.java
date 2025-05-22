package org.example.first_pr.application.auth.services;

import org.example.first_pr.application.auth.entities.User;
import org.example.first_pr.application.auth.exceptions.UserExistsError;
import org.example.first_pr.application.auth.exceptions.UserNotFoundException;
import org.example.first_pr.application.auth.interfaces.IUserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final IUserRepo userRepo;

    public UserService(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getUserById(UUID id) {
        User user = userRepo.getUserById(id);
        if (user == null) { throw new UserNotFoundException(id); }
        return user;
    }

    public User createUser(User userToCreate) {
        User user = userRepo.getUserByUsername(userToCreate.username());
        if (user != null) {
            throw new UserExistsError(userToCreate.username());
        }
        return userRepo.create_user(userToCreate);
    }
}
