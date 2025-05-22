package org.example.first_pr.adapters.db.tables;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.first_pr.application.auth.exceptions.UsernameToLongException;

import java.util.UUID;

@Entity
@Table(name = "users_table")
public class UserTable {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Setter
    @Getter
    @Column(name = "first_name")
    private String firstName;
    @Setter
    @Getter
    @Column(name = "last_name")
    private String lastName;
    @Getter
    @Setter
    @Column(name = "password_hash")
    private String passwordHash;

    public void setUsername(String username) {
        if (username.length() > 255) { throw new UsernameToLongException(username); }
        this.username = username;
    }

    @Getter
    @Column(name = "username")
    private String username;


    public UserTable(String firstName, String lastName, String username, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.setUsername(username);
        this.passwordHash = passwordHash;
    }

    public UserTable() {

    }
}
