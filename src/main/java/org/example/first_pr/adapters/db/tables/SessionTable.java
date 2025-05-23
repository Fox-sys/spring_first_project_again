package org.example.first_pr.adapters.db.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "sessions_table")
public class SessionTable {
    @Id
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "last_activity", nullable = false)
    private Instant lastActivity;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    public SessionTable() {
    }

    public SessionTable(UUID userId, String userAgent, String ipAddress) {
        this.userId = userId;
        this.userAgent = userAgent;
        this.ipAddress = ipAddress;
        this.createdAt = Instant.now();
        this.lastActivity = Instant.now();
        this.isActive = true;
    }
}
