package interaction_api.feign.delivery;

import java.util.UUID;

import interaction_api.feign.cb.DeliveryClientFallback;
import interaction_api.feign.delivery.model.DeliveryDto;
import interaction_api.feign.order.model.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "delivery", fallback = DeliveryClientFallback.class)
@RequestMapping("/api/v1/delivery")
public interface DeliveryFeignClient {

    @PutMapping
    DeliveryDto planDelivery(@RequestBody DeliveryDto deliveryDto);

    @PostMapping("/successful")
    void deliverySuccessful(@RequestBody UUID deliveryId);

    @PostMapping("/picked")
    void deliveryPicked(UUID deliveryId);

    @PostMapping("/failed")
    void deliveryFailed(UUID deliveryId);

    @PostMapping("/cost")
    Double deliveryCost(OrderDto orderDto);
}