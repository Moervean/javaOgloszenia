package main.dao;

import main.model.User;

import java.util.Optional;

public interface UserDao extends AbstractDao<User> {
    public Optional<User> findByLogin(String username);
}
