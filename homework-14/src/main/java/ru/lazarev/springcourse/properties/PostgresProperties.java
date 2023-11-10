package ru.lazarev.springcourse.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@FieldDefaults(level = PRIVATE)
@ConfigurationProperties("properties.postgres")
@Component
public class PostgresProperties {

    String url;
    String username;
    String password;

}