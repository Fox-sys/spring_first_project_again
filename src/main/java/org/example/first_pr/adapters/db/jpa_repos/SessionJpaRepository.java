package org.example.first_pr.adapters.db.jpa_repos;

import org.example.first_pr.adapters.db.tables.SessionTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SessionJpaRepository extends JpaRepository<SessionTable, UUID> {
}
