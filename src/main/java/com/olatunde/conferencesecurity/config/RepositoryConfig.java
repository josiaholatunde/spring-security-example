package com.olatunde.conferencesecurity.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class RepositoryConfig {

    @Value("${migrations.liquibase.change-log}")
    private String dbChangeLogPath;

    @Value("${datasource.user-name}")
    private String datasourceUsername;

    @Value("${datasource.password}")
    private String datasourcePassword;

    @Value("${datasource.url}")
    private String datasourceUrl;

    @Value("${datasource.driver-name}")
    private String dataSourceDriverName;


    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username(datasourceUsername);
        dataSourceBuilder.password(datasourcePassword);
        dataSourceBuilder.url(datasourceUrl);
        dataSourceBuilder.driverClassName(dataSourceDriverName);
        return dataSourceBuilder.build();
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setShouldRun(true);
        springLiquibase.setChangeLog(dbChangeLogPath);
        springLiquibase.setDataSource(dataSource());
        return springLiquibase;
    }
}
