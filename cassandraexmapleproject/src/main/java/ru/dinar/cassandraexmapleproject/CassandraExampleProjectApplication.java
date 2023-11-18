package ru.dinar.cassandraexmapleproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

// @EnableScheduling - для тестов job-ов
@EnableCassandraRepositories
@SpringBootApplication
public class CassandraExampleProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CassandraExampleProjectApplication.class, args);
    }

}
