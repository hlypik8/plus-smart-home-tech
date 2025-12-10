package store;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

@EnableFeignClients
@ConfigurationPropertiesScan
@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class StoreApp {

    private final Environment env;

    public static void main(String[] args) {
        SpringApplication.run(StoreApp.class, args);
    }

    @PostConstruct
    public void log() {
        log.info(">>>> spring.datasource.url={}", env.getProperty("spring.datasource.url"));
        log.info(">>>> spring.datasource.username={}", env.getProperty("spring.datasource.username"));
        log.info(">>>> spring.datasource.schema={}", env.getProperty("spring.datasource.schema"));
        log.info(">>>> spring.jpa.hibernate.ddl-auto={}", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        log.info(">>>> spring.sql.init.mode={}", env.getProperty("spring.sql.init.mode"));
        log.info(">>>> spring.flyway.enabled={}", env.getProperty("spring.flyway.enabled"));
    }
}
