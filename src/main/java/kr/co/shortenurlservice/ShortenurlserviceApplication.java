package kr.co.shortenurlservice;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "kr.co.shortenurlservice.infrastructure.jpa")
public class ShortenurlserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortenurlserviceApplication.class, args);
    }

    @Bean
    @Profile("prod")
    public ApplicationRunner runner(DataSource dataSource) {
        return args -> {
            Connection connection = dataSource.getConnection();
        };
    }
}

