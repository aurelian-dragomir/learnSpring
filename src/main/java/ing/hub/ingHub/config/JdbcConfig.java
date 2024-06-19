package ing.hub.ingHub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public DataSourceTransactionManager jdbcTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SimpleJdbcInsert postJdbcInsert() {
        return new SimpleJdbcInsert(dataSource)
                .withTableName("post");
    }
}
