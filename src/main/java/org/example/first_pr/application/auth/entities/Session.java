package org.example.first_pr.application.auth.entities;

import java.time.Instant;
import java.util.UUID;

public record Session(
        UUID id, UUID userId, String userAgent, String ipAddress, Instant createdAt, Instant lastActivity,
        boolean isActive
) {
}
