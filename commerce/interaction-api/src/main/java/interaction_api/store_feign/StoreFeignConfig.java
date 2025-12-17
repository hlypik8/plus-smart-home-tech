package interaction_api.store_feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {interaction_api.store_feign.StoreFeignClient.class})
public class StoreFeignConfig {
}
