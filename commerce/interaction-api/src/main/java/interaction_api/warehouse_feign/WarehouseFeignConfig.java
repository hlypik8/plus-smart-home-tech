package interaction_api.warehouse_feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "interaction_api.warehouse_feign")
public class WarehouseFeignConfig {
}
