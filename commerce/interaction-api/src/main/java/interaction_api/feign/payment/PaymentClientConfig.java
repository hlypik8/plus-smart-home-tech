package interaction_api.feign.payment;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "interaction_api.feign.payment")
public class PaymentClientConfig {
}
