package ru.dinar.cassandraexmapleproject.service;

import ru.dinar.cassandraexmapleproject.dto.user.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    void delete(UserDto user);

    UserDto find(String surname, String login);

    void update(UserDto user);

}
