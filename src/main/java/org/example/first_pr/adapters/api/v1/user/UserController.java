package org.example.first_pr.adapters.api.v1.user;

import org.example.first_pr.adapters.api.v1.user.schemes.CreateUser;
import org.example.first_pr.application.auth.entities.User;
import org.example.first_pr.application.auth.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getUser(@RequestBody UUID id) {
        return userService.getUserById(id);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody CreateUser user) {
        return userService.createUser(user.toEntity());

    }
}
