package interaction_api.cart_feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {interaction_api.cart_feign.CartFeignClient.class})
public class CartFeignConfig {
}
