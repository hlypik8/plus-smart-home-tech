package interaction_api.feign.store;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "interaction_api.store_feign")
public class StoreFeignConfig {
}
