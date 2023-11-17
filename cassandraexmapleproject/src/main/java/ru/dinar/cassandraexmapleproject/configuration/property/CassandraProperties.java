package ru.dinar.cassandraexmapleproject.configuration.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spring.cassandra")
public class CassandraProperties {

    private String username;
    private String password;
    private String keyspaceName;
    private String contactPoints;

}
