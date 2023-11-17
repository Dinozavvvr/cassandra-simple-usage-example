package ru.dinar.cassandraexmapleproject.service;

import ru.dinar.cassandraexmapleproject.model.user.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

}
