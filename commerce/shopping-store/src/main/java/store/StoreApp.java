package store;

import interaction_api.feign.store.StoreFeignConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Import;

@ConfigurationPropertiesScan
@SpringBootApplication
@Import(StoreFeignConfig.class)
public class StoreApp {
    public static void main(String[] args) {
        SpringApplication.run(StoreApp.class, args);
    }
}
