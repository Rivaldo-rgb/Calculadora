package inmobiliaria.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521/XE");
        config.setUsername("rivi");
        config.setPassword("pass123");
        config.setDriverClassName("oracle.jdbc.OracleDriver");
        
        // Connection pool settings
        config.setMaximumPoolSize(5);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(20000);
        config.setIdleTimeout(300000);
        config.setMaxLifetime(1200000);
        config.setAutoCommit(true);
        
        // Connection test
        config.setConnectionTestQuery("SELECT 1 FROM DUAL");
        config.setLeakDetectionThreshold(60000);
        
        return new HikariDataSource(config);
    }
}
