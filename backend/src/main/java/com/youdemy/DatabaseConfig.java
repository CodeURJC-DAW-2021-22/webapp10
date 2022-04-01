package com.youdemy;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DatabaseConfig {
    private static Logger log = LoggerFactory.getLogger(DatabaseConfig.class);
    @Value("${DATABASE_URL:postgres://iyfppowuslcaqo:661b996dbc1d0774b74d7ff1daa66973d931d1a1225c06d5e97e0c47ca6542e9@ec2-52-73-155-171.compute-1.amazonaws.com:5432/d6rf3u9o1nmqk5}")
    private String databaseUrl;
    @Bean
    @ConditionalOnProperty("DATABASE_URL")
    public DataSource dataSource() throws URISyntaxException {
        log.info("Using database configured in DATABASE_URL=", databaseUrl);
        HikariConfig config = new HikariConfig();
        URI uri = new URI(databaseUrl);
        String url = "jdbc:" + new URI("postgresql", null, uri.getHost(), uri.getPort(), uri.getPath(),
                uri.getQuery(), uri.getFragment()).toString();
        String[] userInfoParts = uri.getUserInfo().split(":");
        String username = userInfoParts[0];
        String password = userInfoParts[1];
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setAutoCommit(false);
        return new HikariDataSource(config);
    }
}