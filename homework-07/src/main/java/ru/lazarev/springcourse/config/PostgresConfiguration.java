package ru.lazarev.springcourse.config;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.lazarev.springcourse.properties.PostgresProperties;

import javax.sql.DataSource;

import static lombok.AccessLevel.PRIVATE;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class PostgresConfiguration {

    private static final String SQL_DRIVER_CLASSNAME = "org.postgresql.Driver";

    PostgresProperties postgresProperties;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(postgresProperties.getUrl());
        dataSource.setUsername(postgresProperties.getUsername());
        dataSource.setPassword(postgresProperties.getPassword());
        dataSource.setDriverClassName(SQL_DRIVER_CLASSNAME);
        return dataSource;
    }
}
