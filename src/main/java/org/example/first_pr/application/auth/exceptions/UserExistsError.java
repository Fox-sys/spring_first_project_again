package org.example.first_pr.application.auth.exceptions;

import org.example.first_pr.application.base.exceptions.AppError;

public class UserExistsError extends AppError {
    public UserExistsError(String username) {
        super(400, "Пользователь " + username + " уже существует");
    }
}
