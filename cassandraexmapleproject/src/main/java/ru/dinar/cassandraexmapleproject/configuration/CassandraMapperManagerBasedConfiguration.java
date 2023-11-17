package ru.dinar.cassandraexmapleproject.configuration;

import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.dinar.cassandraexmapleproject.configuration.property.CassandraProperties;

@Profile("mapper-based")
@Configuration
public class CassandraMapperManagerBasedConfiguration extends CassandraStatementBasedConfiguration {

    public CassandraMapperManagerBasedConfiguration(CassandraProperties cassandraProperties) {
        super(cassandraProperties);
    }

    /** Создаем MappingManager для Datastax ORM */
    @Bean
    public MappingManager mappingManager(Session session) {
        return new MappingManager(session);
    }

}
