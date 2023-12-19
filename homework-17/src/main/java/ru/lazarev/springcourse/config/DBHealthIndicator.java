package ru.lazarev.springcourse.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DBHealthIndicator implements HealthIndicator {

    private final JdbcTemplate jdbcTemplate;

    public DBHealthIndicator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Health health() {
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT * FROM pg_stat_activity");
            return Health.up().withDetail("Database Info", result).build();
        } catch (Exception ex) {
            return Health.down().withDetail("Error Message", ex.getMessage()).build();
        }
    }
}
