package interaction_api.cart_feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "interaction_api.cart_feign")
public class CartFeignConfig {
}
