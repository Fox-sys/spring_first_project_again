package org.example.first_pr.application.auth.services;

import org.example.first_pr.application.auth.entities.User;
import org.example.first_pr.application.auth.exceptions.UserExistsErrorException;
import org.example.first_pr.application.auth.exceptions.UserNotFoundByIdException;
import org.example.first_pr.application.auth.interfaces.IUserRepo;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final IUserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(IUserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserById(UUID id) {
        User user = userRepo.getUserById(id);
        if (user == null) { throw new UserNotFoundByIdException(id); }
        return user;
    }

    public User getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }

    public User createUser(User userToCreate) {
        User user = userRepo.getUserByUsername(userToCreate.getUsername());
        if (user != null) {
            throw new UserExistsErrorException(userToCreate.getUsername());
        }
        userToCreate.setPasswordHash(passwordEncoder.encode(userToCreate.getPassword()));
        User createdUser = userRepo.create_user(userToCreate);
        createdUser.setPasswordHash(null);
        return createdUser;
    }

    @Override
    public User loadUserByUsername(String username) throws UserNotFoundByIdException {
        return getUserByUsername(username);
    }
}
