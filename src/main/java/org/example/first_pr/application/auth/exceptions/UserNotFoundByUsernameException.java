package org.example.first_pr.application.auth.exceptions;

import org.example.first_pr.application.base.exceptions.AppError;

public class UserNotFoundByUsernameException extends AppError {
    public UserNotFoundByUsernameException(String username) {
        super(404, username);
    }
}
