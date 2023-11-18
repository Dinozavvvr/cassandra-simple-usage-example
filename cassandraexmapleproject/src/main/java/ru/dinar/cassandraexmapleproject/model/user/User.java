package ru.dinar.cassandraexmapleproject.model.user;

import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

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
@com.datastax.driver.mapping.annotations.Table(keyspace = "cassandra_example_project", name = "user",
        readConsistency = "QUORUM",
        writeConsistency = "QUORUM",
        caseSensitiveKeyspace = false,
        caseSensitiveTable = false)
@Table
public class User {

    private UUID id;

    @com.datastax.driver.mapping.annotations.PartitionKey(0)
    @PrimaryKeyColumn(name = "surname", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String surname;

    private String name;

    @com.datastax.driver.mapping.annotations.ClusteringColumn(0)
    @PrimaryKeyColumn(name = "login", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    private String login;

    private String password;

    @com.datastax.driver.mapping.annotations.Column(name = "role")
    @Column("role")
    private Set<String> userRoles;

    // Токен от partition key (surname) в рамках cassandra
    @com.datastax.driver.mapping.annotations.Transient
    @Transient
    private String token;

}
