package ru.dinar.cassandraexmapleproject.model.user;

import com.datastax.driver.mapping.annotations.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Пользователь
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
// https://docs.datastax.com/en/developer/java-driver/3.0/manual/object_mapper/creating/
@Table(keyspace = "cassandra_example_project", name = "user",
        readConsistency = "QUORUM",
        writeConsistency = "QUORUM",
        caseSensitiveKeyspace = false,
        caseSensitiveTable = false)
public class User {

    private UUID id;

    @PartitionKey(0)
    private String surname;

    private String name;

    private String login;

    private String password;

    @Column(name = "role")
    private Set<String> userRoles;

    // Токен от partition key (surname) в рамках cassandra
    @Transient
    private String token;

}
