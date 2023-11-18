package ru.dinar.cassandraexmapleproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dinar.cassandraexmapleproject.mapper.UserDtoUserMapper;
import ru.dinar.cassandraexmapleproject.model.user.User;
import ru.dinar.cassandraexmapleproject.dto.user.UserDto;
import ru.dinar.cassandraexmapleproject.repository.UserRepository;

import java.util.List;

/**
 * Сервис для работы с пользователями
 * */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    UserDtoUserMapper userMapper = UserDtoUserMapper.INSTANCE;

    @Override
    public List<UserDto> findAll() {
        return userMapper.from(userRepository.findAll());
    }

    @Override
    public void delete(UserDto user) {
        userRepository.delete(userMapper.to(user));
    }

    @Override
    public UserDto find(String surname, String login) {
        return userMapper.from(userRepository.read(surname, login));
    }

    @Override
    public void update(UserDto user) {
        User persistentUser = userRepository.read(user.getSurname(), user.getLogin());
        if (persistentUser == null) {
            return;
        }

        update(persistentUser, user);
    }

    private void update(User persistentUser, UserDto user) {
        persistentUser.setName(user.getName());

        userRepository.update(userMapper.to(user));
    }

}
