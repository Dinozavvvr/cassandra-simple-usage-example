package ru.dinar.cassandraexmapleproject.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.dinar.cassandraexmapleproject.model.user.User;

import java.util.List;

@Profile("jpa-based")
@Repository
@RequiredArgsConstructor
public class DataJpaBasedUserRepository implements UserRepository {

    private final CassandraDataJpaBasedUserRepository repository;

    @Override
    public void create(User user) {
        repository.save(user);
    }

    @Override
    public User read(String surname, String login) {
        return repository.findBySurnameAndLogin(surname, login).orElse(null);
    }

    @Override
    public void update(User user) {
        repository.save(user);
    }

    @Override
    public void delete(User user) {
        repository.deleteUsersByLoginAndSurname(user.getLogin(), user.getSurname());
    }

    @Override
    public List<User> findAll() {
        return repository.findAll(Pageable.ofSize((int) repository.count())).getContent();
    }

}
