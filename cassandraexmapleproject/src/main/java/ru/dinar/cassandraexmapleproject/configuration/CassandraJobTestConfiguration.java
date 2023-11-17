package ru.dinar.cassandraexmapleproject.configuration;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.dinar.cassandraexmapleproject.configuration.property.CassandraProperties;

@Configuration
@RequiredArgsConstructor
@Profile("jobs-test")
public class CassandraJobTestConfiguration {

    private final CassandraProperties cassandraProperties;

    /** Создаем сессию с подключением к указанному keyspace */
    @Bean
    public Session sessionOne(Cluster cluster) {
        return cluster.connect(cassandraProperties.getKeyspaceName());
    }

    /** Создаем сессию с подключением к указанному keyspace */
    @Bean
    public Session sessionTwo(Cluster cluster) {
        return cluster.connect(cassandraProperties.getKeyspaceName());
    }

}
