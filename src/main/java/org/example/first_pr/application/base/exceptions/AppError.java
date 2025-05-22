package org.example.first_pr.application.base.exceptions;

public class AppError extends RuntimeException {
    private final int code;
    private final String message;
    public AppError(int code, String message)
    {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
