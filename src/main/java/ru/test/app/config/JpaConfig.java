package ru.test.app.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {
        "ru.test.app.repository",
        "ru.test.app.service"
})
@EnableTransactionManagement(proxyTargetClass = true)
public class JpaConfig implements TransactionManagementConfigurer {

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(20);
        config.setDriverClassName("org.h2.Driver");
        config.setJdbcUrl("jdbc:h2:mem:h2db;INIT=" +
                "RUNSCRIPT FROM 'classpath:sql/h2/create-database.sql'\\;" +
                "RUNSCRIPT FROM 'classpath:sql/h2/create-tables.sql'\\;"
        );
        config.setUsername("sa");
        config.setPassword("");
        config.setConnectionTestQuery("SELECT 1");
        return new HikariDataSource(config);
    }

    @Override
    public TransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
