package ru.dinar.cassandraexmapleproject.util;

import com.datastax.driver.core.Row;

public interface CassandraCustomRowMapper<T> {

    T mapRow(Row row);

}
