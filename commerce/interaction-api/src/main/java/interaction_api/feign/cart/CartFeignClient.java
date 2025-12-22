package interaction_api.feign.cart;

import interaction_api.feign.cart.model.ChangeProductQuantityRequest;
import interaction_api.feign.cart.model.ShoppingCartDto;
import interaction_api.feign.cart.model.exception.NoProductsInShoppingCartException;
import interaction_api.feign.cart.model.exception.NotAuthorizedUserException;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@FeignClient(name = "shopping-cart")
@RequestMapping("/api/v1/shopping-cart")
public interface CartFeignClient {

    @GetMapping
    ShoppingCartDto get(@RequestParam String username) throws NotAuthorizedUserException;

    @PutMapping
    ShoppingCartDto add(@RequestParam String username, @RequestBody Map<UUID, Long> products) throws NotAuthorizedUserException;

    @DeleteMapping
    void deleteCart(@RequestParam String username) throws NotAuthorizedUserException;

    @PostMapping("/remove")
    ShoppingCartDto removeProducts(@RequestParam String username, @RequestBody List<UUID> productIds) throws NotAuthorizedUserException;

    @PostMapping("/change-quantity")
    ShoppingCartDto quantityUpdate(@RequestParam String username,
                                   @RequestBody @Valid ChangeProductQuantityRequest request) throws NoProductsInShoppingCartException, NotAuthorizedUserException;
}
