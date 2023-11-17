package ru.dinar.cassandraexmapleproject.repository;

import ru.dinar.cassandraexmapleproject.model.user.User;

import java.util.List;

/**
 * Репозиторий для рег
 * */
public interface UserRepository {

    void create(User user);

    List<User> findAll();

}
