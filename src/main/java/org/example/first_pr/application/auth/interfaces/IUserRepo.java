package org.example.first_pr.application.auth.interfaces;

import org.example.first_pr.application.auth.entities.User;

import java.util.List;
import java.util.UUID;

public interface IUserRepo {
    public User getUserById(UUID id);
    public List<User> getUsers();
}
