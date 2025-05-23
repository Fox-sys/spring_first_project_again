package org.example.first_pr.adapters.db.repositories;

import org.example.first_pr.adapters.db.jpa_repos.SessionJpaRepository;
import org.example.first_pr.adapters.db.tables.SessionTable;
import org.example.first_pr.application.auth.entities.Session;
import org.example.first_pr.application.auth.entities.User;
import org.example.first_pr.application.auth.entities.UserAuthDto;
import org.example.first_pr.application.auth.interfaces.ISessionRepo;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SessionRepo implements ISessionRepo {
    private final SessionJpaRepository jpa;

    public SessionRepo(SessionJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Session save(User user, UserAuthDto dto) {
        SessionTable s = new SessionTable();
        s.setId(UUID.randomUUID());
        s.setUserId(user.getId());
        s.setUserAgent(dto.userAgent());
        s.setIpAddress(dto.ipAddress());
        s.setCreatedAt(Instant.now());
        s.setLastActivity(Instant.now());
        s.setActive(true);

        return toEntity(jpa.save(s));
    }

    @Override
    public Session getById(UUID id) {
        Optional<SessionTable> found = jpa.findById(id);
        return found.map(this::toEntity).orElse(null);
    }

    @Override
    public void deactivate(UUID sessionId) {
        Optional<SessionTable> found = jpa.findById(sessionId);
        found.ifPresent(session -> {
            session.setActive(false);
            session.setLastActivity(Instant.now());
            jpa.save(session);
        });
    }

    private Session toEntity(SessionTable s) {
        return new Session(s.getId(), s.getUserId(), s.getUserAgent(), s.getIpAddress(), s.getCreatedAt(), s.getLastActivity(), s.isActive());
    }
}
