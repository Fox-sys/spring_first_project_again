package org.example.first_pr.adapters.db.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.first_pr.adapters.db.tables.UserTable;
import org.example.first_pr.application.auth.entities.User;
import org.example.first_pr.application.auth.interfaces.IUserRepo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class UserRepo implements IUserRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserById(UUID id) {
        UserTable userTable = entityManager.find(UserTable.class, id);
        if (userTable == null) return null;
        return toUser(userTable);
    }

    @Override
    public List<User> getUsers() {
        List<UserTable> userTables = entityManager.createQuery("SELECT u FROM UserTable u", UserTable.class)
                .getResultList();

        return userTables.stream()
                .map(ut -> new User(ut.getId(), ut.getFirstName(), ut.getLastName(), ut.getUsername(), ut.getPasswordHash()))
                .collect(Collectors.toList());
    }

    @Override
    public User getUserByUsername(String username) {
        List<UserTable> result = entityManager.createQuery(
                        "SELECT u FROM UserTable u WHERE u.username = :username", UserTable.class)
                .setParameter("username", username)
                .getResultList();

        if (result.isEmpty()) return null;

        return toUser(result.get(0));
    }

    @Override
    @Transactional
    public User create_user(User user) {
        UserTable userTable = new UserTable(user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword());

        entityManager.persist(userTable);
        entityManager.flush();

        return toUser(userTable);
    }

    private User toUser(UserTable user) {
        return new User(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPasswordHash());
    }
}
