package ru.dinar.cassandraexmapleproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dinar.cassandraexmapleproject.model.user.User;
import ru.dinar.cassandraexmapleproject.repository.UserRepository;

import java.util.List;

/**
 * Сервис для работы с пользователями
 * */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
