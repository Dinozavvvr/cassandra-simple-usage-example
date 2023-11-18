package ru.dinar.cassandraexmapleproject.service;

import com.datastax.driver.core.utils.UUIDs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dinar.cassandraexmapleproject.dto.user.SignUpRequestDto;
import ru.dinar.cassandraexmapleproject.mapper.SignUpRequestDtoUserMapper;
import ru.dinar.cassandraexmapleproject.model.user.User;
import ru.dinar.cassandraexmapleproject.model.user.UserRole;
import ru.dinar.cassandraexmapleproject.repository.UserRepository;

import java.util.HashSet;

/**
 * Сервис для регистрации новых пользователей
 * */
@Service
@RequiredArgsConstructor
public class PlaneSignUpService implements SignUpService {

    private final UserRepository userRepository;

    public void signUp(SignUpRequestDto signUpRequestDto) {
        userRepository.create(getFromSignUpRequest(signUpRequestDto));
    }

    private User getFromSignUpRequest(SignUpRequestDto signUpRequestDto) {
        User newUser = SignUpRequestDtoUserMapper.INSTANCE.from(signUpRequestDto);

        newUser.setId(UUIDs.timeBased());
        newUser.setUserRoles(new HashSet<>());
        newUser.getUserRoles().add(UserRole.USER.name());

        return newUser;
    }

}
