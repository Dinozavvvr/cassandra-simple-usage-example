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
    private final CassandraCustomRowMapper<User> userRowMapper = row -> {
        if (row == null) return null;

        return User.builder()
            .id(row.getUUID("id"))
            .surname(row.getString("surname"))
            .name(row.getString("name"))
            .login(row.getString("login"))
            .userRoles(row.getSet("role", String.class))
            .token(row.getPartitionKeyToken().getValue().toString())
            .build();
    };


    private static final String INSERT_USER = "insert into cassandra_example_project.user(surname, name, login, password, role, id) " +
            "values (:surname, :name, :login, :pass, :roles, now())";

    @Override
    public void create(User user) {
        session.execute(new SimpleStatement(INSERT_USER, prepareParams(user)));
    }


    private static final String GET_USER = "select id, name, surname, login, role, token(surname) from cassandra_example_project.user " +
            "where surname = :surname and login = :login";

    @Override
    public User read(String surname, String login) {
        Map<String, Object> params = new HashMap<>();
        params.put("surname", surname);
        params.put("login", login);

        return userRowMapper.mapRow(session.execute(new SimpleStatement(GET_USER, params)).one());
    }

    private static final String UPDATE_USER = "insert into cassandra_example_project.user(surname, name, login) " +
            "values (:surname, :name, :login)";

    @Override
    public void update(User user) {
        session.execute(new SimpleStatement(UPDATE_USER, prepareParams(user)));
    }

    private static final String DELETE_USER = "delete from cassandra_example_project.user where " +
            "login = :login and surname = :surname";

    @Override
    public void delete(User user) {
        session.execute(new SimpleStatement(DELETE_USER, prepareParams(user)));
    }

    private static final String SELECT_ALL_USERS = "select id, name, surname, login, role, token(surname) " +
            "from cassandra_example_project.user";

    @Override
    public List<User> findAll() {
        Statement statement = new SimpleStatement(SELECT_ALL_USERS);
        List<Row> foundUsersRows = session.execute(statement).all();

        return foundUsersRows.stream().map(userRowMapper::mapRow).collect(Collectors.toList());
    }

    private Map<String, Object> prepareParams(User user) {
        Map<String, Object> userQueryParams = new HashMap<>();

        userQueryParams.put("surname", user.getSurname());
        userQueryParams.put("name", user.getName());
        userQueryParams.put("login", user.getLogin());
        userQueryParams.put("pass", user.getPassword());
        userQueryParams.put("roles", user.getUserRoles());

        return userQueryParams;
    }

}
