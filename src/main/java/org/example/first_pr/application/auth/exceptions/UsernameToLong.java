package org.example.first_pr.application.auth.exceptions;

import org.example.first_pr.application.base.exceptions.AppError;

public class UsernameToLong extends AppError {
    public UsernameToLong(String username) {
        super(400, "Имя пользователя \"" + username + "\" слишком длинное");
    }
}
