package org.example.first_pr.adapters.api.v1.user;

import org.example.first_pr.adapters.api.v1.user.dtos.UserDTO;
import org.example.first_pr.application.auth.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserDTO getUser() {
        return new UserDTO(userService.get_current_user());
    }
}
