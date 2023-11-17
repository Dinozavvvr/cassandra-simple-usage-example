package ru.dinar.cassandraexmapleproject.repository;

import com.datastax.driver.core.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.dinar.cassandraexmapleproject.model.user.User;
import ru.dinar.cassandraexmapleproject.util.CassandraCustomRowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Самый низкоуровневый репозиторий для работы с Cassandra основанный на Statement
 * */
@Component
@Profile("statement-based")
@RequiredArgsConstructor
public class StatementBasedUserRepository implements UserRepository {

    private final Session session;
    private final CassandraCustomRowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getUUID("id"))
            .surname(row.getString("surname"))
            .name(row.getString("name"))
            .login(row.getString("login"))
            .userRoles(row.getSet("role", String.class))
            .token(row.getPartitionKeyToken().getValue().toString())
            .build();


    private static final String INSERT_USER = "insert into cassandra_example_project.user(surname, name, login, password, role, id) " +
            "values (:surname, :name, :login, :pass, :roles, now())";

    @Override
    public void create(User user) {
        session.execute(new SimpleStatement(INSERT_USER, prepareCreateParams(user)));
    }

    private Map<String, Object> prepareCreateParams(User user) {
        Map<String, Object> userQueryParams = new HashMap<>();

        userQueryParams.put("surname", user.getSurname());
        userQueryParams.put("name", user.getName());
        userQueryParams.put("login", user.getLogin());
        userQueryParams.put("pass", user.getPassword());
        userQueryParams.put("roles", user.getUserRoles());

        return userQueryParams;
    }


    private static final String SELECT_ALL_USERS = "select id, name, surname, login, role, token(surname) " +
            "from cassandra_example_project.user";

    @Override
    public List<User> findAll() {
        Statement statement = new SimpleStatement(SELECT_ALL_USERS);
        List<Row> foundUsersRows = session.execute(statement).all();

        return foundUsersRows.stream().map(userRowMapper::mapRow).collect(Collectors.toList());
    }

}
