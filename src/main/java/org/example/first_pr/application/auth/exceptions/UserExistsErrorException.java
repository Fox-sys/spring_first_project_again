package org.example.first_pr.application.auth.exceptions;

import org.example.first_pr.application.base.exceptions.AppError;

public class UserExistsErrorException extends AppError {
    public UserExistsErrorException(String username) {
        super(400, "Пользователь " + username + " уже существует");
    }
}
