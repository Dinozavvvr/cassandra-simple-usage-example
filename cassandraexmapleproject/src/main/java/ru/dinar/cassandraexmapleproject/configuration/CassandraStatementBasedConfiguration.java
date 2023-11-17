package ru.dinar.cassandraexmapleproject.configuration;
import com.datastax.driver.core.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.dinar.cassandraexmapleproject.configuration.property.CassandraProperties;

/**
 * Конфигурация для всех необходимых компонентов для работы с Cassandra
 <a href="https://docs.datastax.com/en/developer/java-driver/3.0/">DataStax java driver for Apache Cassandra</a>
 */
@Configuration
@RequiredArgsConstructor
@Profile("statement-based")
public class CassandraStatementBasedConfiguration {

    private final CassandraProperties cassandraProperties;


    /** Провайдер аутентификации */
    @Bean
    public AuthProvider authProvider() {
        return new PlainTextAuthProvider(cassandraProperties.getUsername(), cassandraProperties.getPassword());
    }

    /** Создаем TCP подключение к Cassandra */
    @Bean
    public Cluster cluster(AuthProvider authProvider) {
        return Cluster.builder()
                .withAuthProvider(authProvider)
                .addContactPoints(cassandraProperties.getContactPoints())
                .build();
    }

    /** Создаем сессию с подключением к указанному keyspace */
    @Bean
    public Session session(Cluster cluster) {
        return cluster.connect(cassandraProperties.getKeyspaceName());
    }

}
