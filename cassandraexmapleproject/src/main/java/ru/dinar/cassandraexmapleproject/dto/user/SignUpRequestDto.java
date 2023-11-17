package ru.dinar.cassandraexmapleproject.dto.user;

import lombok.Getter;
import lombok.Setter;

/**
 * Dto на регистрацию пользователя
 * */
@Getter
@Setter
public class SignUpRequestDto {

    /**
     * Логин
     * */
    private String login;

    /**
     * Пароль
     * */
    private String password;

    /**
     * Имя
     * */
    private String name;

    /**
     * Фамилия
     * */
    private String surname;

}
