package org.example.first_pr.application.auth.exceptions;

import org.example.first_pr.application.base.exceptions.AppError;

import java.util.UUID;

public class UserNotFoundException extends AppError {
  public UserNotFoundException(UUID id) {
    super(404, "Пользователя с id \"" + id + "\" не существует");
  }
}
