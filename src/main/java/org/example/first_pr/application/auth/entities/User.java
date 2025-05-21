package org.example.first_pr.application.auth.entities;

import java.util.UUID;

public record User(UUID id, String firstName, String lastName) { }
