package ru.dinar.cassandraexmapleproject.repository;

import ru.dinar.cassandraexmapleproject.model.user.User;

import java.util.List;

/**
 * Репозиторий для сущности User
 * */
public interface UserRepository {

    void create(User user);

    User read(String surname, String login);

    void update(User user);

    void delete(User user);

    List<User> findAll();

}
