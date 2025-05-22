package org.example.first_pr.adapters.api.v1.user;

import org.example.first_pr.adapters.api.v1.user.schemes.CreateUser;
import org.example.first_pr.adapters.api.v1.user.schemes.UserResponse;
import org.example.first_pr.application.auth.entities.User;
import org.example.first_pr.application.auth.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get_current_user")
    public UserResponse getCurrentUser(@AuthenticationPrincipal User user) {
        return new UserResponse(userService.getUserById(user.getId()));
    }

    @PostMapping("/create")
    public UserResponse createUser(@RequestBody CreateUser user) {
        return new UserResponse(userService.createUser(user.toEntity()));
    }
}
