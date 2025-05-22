package org.example.first_pr.adapters.db.tables;

import jakarta.persistence.*;
import org.example.first_pr.application.auth.exceptions.UsernameToLong;

import java.util.UUID;

@Entity
@Table(name = "users_table")
public class UserTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username.length() > 255) { throw new UsernameToLong(username); }
        this.username = username;
    }

    @Column(name = "username")
    private String username;


    public UserTable(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.setUsername(username);
    }

    public UserTable() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
