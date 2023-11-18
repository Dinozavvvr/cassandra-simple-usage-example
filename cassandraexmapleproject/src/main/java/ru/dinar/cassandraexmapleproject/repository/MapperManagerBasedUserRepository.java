package ru.dinar.cassandraexmapleproject.repository;

import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.dinar.cassandraexmapleproject.model.user.User;

import java.util.List;

@Component
@Profile("mapper-based")
@RequiredArgsConstructor
public class MapperManagerBasedUserRepository implements UserRepository {

    private final MappingManager mappingManager;

    private Mapper<User> userMapper;

    private UserAccessor userAccessor;

    @PostConstruct
    void init() {
        this.userMapper = mappingManager.mapper(User.class);
        this.userAccessor = mappingManager.createAccessor(UserAccessor.class);
    }

    @Override
    public void create(User user) {
        userMapper.save(user);
    }

    @Override
    public User read(String surname, String login) {
        return userMapper.get(surname, login);
    }

    @Override
    public void update(User user) {
        userMapper.save(user);
    }

    @Override
    public void delete(User user) {
        userMapper.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userAccessor.findAll().all();
    }

    @Accessor
    public interface UserAccessor {
        @Query("select id, name, surname, login, role, token(surname) " +
                "from cassandra_example_project.user")
        Result<User> findAll();
    }

}
