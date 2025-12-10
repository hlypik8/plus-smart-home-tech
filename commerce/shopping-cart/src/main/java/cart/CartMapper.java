package cart;

import interaction_api.cart.model.ShoppingCartDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CartMapper {
    @Mapping(source = "cartId", target = "shoppingCartId")
    ShoppingCartDto toDto(Cart cart);
}