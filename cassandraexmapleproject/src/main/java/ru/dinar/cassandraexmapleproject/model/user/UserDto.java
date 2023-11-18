package ru.dinar.cassandraexmapleproject.model.user;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class UserDto {

    private String surname;

    private String name;

    private String login;

    private UUID id;

    private Set<String> userRoles;

}
