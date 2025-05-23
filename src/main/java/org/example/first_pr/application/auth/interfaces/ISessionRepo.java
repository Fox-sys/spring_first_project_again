package org.example.first_pr.application.auth.interfaces;

import org.example.first_pr.application.auth.entities.Session;
import org.example.first_pr.application.auth.entities.User;
import org.example.first_pr.application.auth.entities.UserAuthDto;

import java.util.Optional;
import java.util.UUID;

public interface ISessionRepo {
    Session save(User user, UserAuthDto dto);

    Session getById(UUID id);

    void deactivate(UUID sessionId);
}
