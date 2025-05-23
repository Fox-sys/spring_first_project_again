package org.example.first_pr.application.auth.exceptions;

import org.example.first_pr.application.base.exceptions.AppError;

public class SessionUnprocessable extends AppError {
    public SessionUnprocessable() {
        super(401, "Session is unprocessable");
    }

    public SessionUnprocessable(String message) {
        super(401, message);

    }
}
