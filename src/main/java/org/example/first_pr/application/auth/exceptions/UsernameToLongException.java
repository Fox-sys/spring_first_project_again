package org.example.first_pr.application.auth.exceptions;

import org.example.first_pr.application.base.exceptions.AppError;

public class UsernameToLongException extends AppError {
    public UsernameToLongException(String username) {
        super(400, "Имя пользователя \"" + username + "\" слишком длинное");
    }
}
