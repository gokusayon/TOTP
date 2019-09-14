package com.bits.ns.auth;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

//@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.type}")
    private String type;

    @Value("${spring.datasource.driver-class}")
    private String driverClass;

    @Bean
    public DataSource dataSource() {
    	EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder
                .setType(EmbeddedDatabaseType.H2)
                .build();
		/*
		 * if (type.equals("MYSQL")) { return DataSourceBuilder .create()
		 * .username(username) .password(password) .url(url)
		 * .driverClassName(driverClass) .build(); } else {
		 * 
		 * }
		 */
    }
}