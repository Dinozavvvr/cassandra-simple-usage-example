package ru.dinar.cassandraexmapleproject.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import ru.dinar.cassandraexmapleproject.model.user.User;

import java.util.Optional;

@Repository
public interface CassandraDataJpaBasedUserRepository extends CassandraRepository<User, String> {

    void deleteUsersByLoginAndSurname(String login, String surname);

    Optional<User> findBySurnameAndLogin(String login, String surname);

}
