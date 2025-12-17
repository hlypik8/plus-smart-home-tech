package interaction_api.warehouse_feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {interaction_api.warehouse_feign.WarehouseFeignClient.class})
public class WarehouseFeignConfig {
}
