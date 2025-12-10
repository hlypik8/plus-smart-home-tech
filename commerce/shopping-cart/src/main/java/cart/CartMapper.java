package cart.nes;

import interaction_api.cart.model.ShoppingCartDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CartMapper {
    ShoppingCartDto toDto(Cart cart);
}