package interaction_api.cart.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Builder
@Data
public class ShoppingCartDto {

    private UUID cartId;

    private Map<UUID, Integer> products;
}
