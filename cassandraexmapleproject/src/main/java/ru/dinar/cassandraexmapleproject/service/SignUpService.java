package ru.dinar.cassandraexmapleproject.service;

import ru.dinar.cassandraexmapleproject.dto.user.SignUpRequestDto;

public interface SignUpService {

    void signUp(SignUpRequestDto signUpRequestDto);

}
