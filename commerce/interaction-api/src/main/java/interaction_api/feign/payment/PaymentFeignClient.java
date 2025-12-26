package interaction_api.feign.payment;

import java.util.UUID;

import interaction_api.feign.cb.PaymentClientFallback;
import interaction_api.feign.order.model.OrderDto;
import interaction_api.feign.payment.model.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "payment", fallback = PaymentClientFallback.class)
@RequestMapping("/api/v1/payment")
public interface PaymentFeignClient {

    @PostMapping
    PaymentDto processPayment(@RequestBody OrderDto orderDto);

    @PostMapping("/totalCost")
    Double getTotalCost(@RequestBody OrderDto orderDto);

    @PostMapping("/refund")
    void paymentSuccess(@RequestBody UUID paymentId);

    @PostMapping("/productCost")
    Double getProductsCost(@RequestBody OrderDto orderDto);

    @PostMapping("/failed")
    void paymentFailed(@RequestBody UUID paymentId);
}
